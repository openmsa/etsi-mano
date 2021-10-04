/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.mano.geoms.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LatLonPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.ubiqube.etsi.mano.model.SearchResult;
import com.ubiqube.mano.geoms.beans.GeoFile;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class IndexManager {

	private static final String DIRECTORY = "geo-index";

	private static final Logger LOG = LoggerFactory.getLogger(IndexManager.class);

	private final Path indexPath = Paths.get(DIRECTORY);

	public IndexManager() {
		if (indexPath.toFile().exists()) {
			LOG.info("Skipping index creation.");
			return;
		}
		try {
			buildIndex(indexPath);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void buildIndex(final Path indexPath) throws IOException {
		indexPath.toFile().mkdirs();
		try (final FSDirectory directory = FSDirectory.open(indexPath); final Analyzer analyzer = new StandardAnalyzer()) {
			final IndexWriterConfig config = new IndexWriterConfig(analyzer);
			try (final IndexWriter iwriter = new IndexWriter(directory, config)) {
				doIndex(iwriter);
			}
		}
	}

	private static void doIndex(final IndexWriter iwriter) {
		final List<GeoFile> files = getData();
		files.forEach(x -> {
			final Document doc = new Document();
			doc.add(new Field("city", x.getCity(), TextField.TYPE_STORED));
			doc.add(new LatLonPoint("latlon", x.getLat(), x.getLng()));
			doc.add(new StoredField("lat", x.getLat()));
			doc.add(new StoredField("lon", x.getLng()));
			doc.add(new Field("country", x.getCountry(), TextField.TYPE_STORED));
			doc.add(new Field("iso2", x.getIso2(), TextField.TYPE_STORED));
			doc.add(new Field("iso3", x.getIso3(), TextField.TYPE_STORED));
			doc.add(new Field("id", "" + x.getId(), TextField.TYPE_STORED));
			try {
				iwriter.addDocument(doc);
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private static List<GeoFile> getData() {
		final InputStream resource = ClassLoader.getSystemResourceAsStream("worldcities.csv");
		try (final Reader reader = new InputStreamReader(resource)) {
			final MappingStrategy<GeoFile> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
			mappingStrategy.setType(GeoFile.class);
			return new CsvToBeanBuilder<GeoFile>(reader)
					.withMappingStrategy(mappingStrategy)
					.withType(GeoFile.class).build().parse();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public SearchResult search(final String countryCode, final String city) throws IOException {
		try (final FSDirectory directory = FSDirectory.open(indexPath); final DirectoryReader ireader = DirectoryReader.open(directory)) {
			final IndexSearcher isearcher = new IndexSearcher(ireader);
			final Query query = createQuery(countryCode, city);
			final ScoreDoc[] hits = isearcher.search(query, 1).scoreDocs;
			LOG.info("Hit: {} , for query: {}", hits.length, query);
			final SearchResult sr = new SearchResult();
			for (final ScoreDoc hit : hits) {
				final Document hitDoc = isearcher.doc(hit.doc);
				final StoredField lat = (StoredField) hitDoc.getField("lat");
				sr.setLat(lat.numericValue().doubleValue());
				final StoredField lon = (StoredField) hitDoc.getField("lon");
				sr.setLng(lon.numericValue().doubleValue());
			}
			ireader.close();
			directory.close();
			return sr;
		}
	}

	private static Query createQuery(final String countryCode, final String city) throws IOException {
		final Builder bq = new BooleanQuery.Builder();
		if (null != countryCode) {
			final Query pq = tokenize("country", countryCode);
			bq.add(pq, Occur.MUST);
		}
		if (null != city) {
			final Query pq = tokenize("city", city);
			bq.add(pq, Occur.MUST);
		}
		return bq.build();
	}

	private static PhraseQuery tokenize(final String fieldName, final String value) throws IOException {
		try (final Analyzer analyzer = new StandardAnalyzer()) {
			try (final TokenStream tokenStream = analyzer.tokenStream(fieldName, value)) {
				final CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
				tokenStream.reset();
				final org.apache.lucene.search.PhraseQuery.Builder pBuilder = new PhraseQuery.Builder();
				while (tokenStream.incrementToken()) {
					final String term = charTermAttribute.toString();
					pBuilder.add(new Term(fieldName, term));
				}
				return pBuilder.build();
			}
		}
	}

	private void search2() {
		final QueryBuilder qb = new QueryBuilder(null);
	}

}

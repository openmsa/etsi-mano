FROM openmsa/openmsa:1.3

RUN wget -q https://getcomposer.org/download/1.8.6/composer.phar
RUN chmod +x composer.phar && mv composer.phar /usr/bin/composer

RUN	cd /opt/fmc_repository &&\
	git clone https://github.com/openmsa/etsi-mano		;\
	PACKAGE=ETSI-MANO					;\
	PKG_DIR=../etsi-mano/WORKFLOWS/$PACKAGE			;\
	ln -s $PKG_DIR			Process/$PACKAGE	;\
	ln -s $PKG_DIR/conf		Configuration/$PACKAGE	;\
	:

RUN cd /opt/fmc_repository/etsi-mano/WORKFLOWS/ETSI-MANO && composer install

COPY docker/msa/license/* /opt/ubiqube/
RUN process-license.sh /opt/ubiqube/license{.lic,}		;\
	ls -l /opt/ubiqube/license*
RUN chown ncuser: /opt/ubiqube/license*


CMD ["/sbin/init"]

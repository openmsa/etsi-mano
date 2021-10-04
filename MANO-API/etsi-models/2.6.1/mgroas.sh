find . -name *.java -exec sed -i 's/import io.swagger.annotations.ApiModel;/import io.swagger.v3.oas.annotations.media.Schema;/' {} \;
find . -name *.java -exec sed -i 's/import io.swagger.annotations.ApiModelProperty;//' {} \;
find . -name *.java -exec sed -i 's/@ApiModel/@Schema/' {} \;
find . -name *.java -exec sed -i 's/@SchemaProperty/@Schema/' {} \;
find . -name *.java -exec sed -i 's/value = "/description = "/' {} \;


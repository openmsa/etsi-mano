FROM openmsa/openmsa:1.2

RUN wget -q https://getcomposer.org/download/1.8.6/composer.phar
RUN chmod +x composer.phar && mv composer.phar /usr/bin/composer

RUN	cd /opt/fmc_repository &&\
	git clone https://github.com/openmsa/etsi-mano		;\
	PACKAGE=ETSI-MANO					;\
	PKG_DIR=../etsi-mano/WORKFLOWS/$PACKAGE			;\
	ln -s $PKG_DIR			Process/$PACKAGE	;\
	ln -s $PKG_DIR/Configuration	Configuration/$PACKAGE	;\
	:

RUN cd /opt/fmc_repository/etsi-mano/WORKFLOWS/ETSI-MANO && composer install

COPY docker/msa/license/* /opt/ubiqube/
RUN chown ncuser: /opt/ubiqube/license*

RUN ln -s /opt/sms/bin/check_services_status.sh /usr/bin/


CMD ["/sbin/init"]

FROM openmsa/openmsa:1.2

RUN	cd /opt/fmc_repository &&\
	git clone https://github.com/openmsa/etsi-mano &&\
	cd Process &&\
	ln -s ../etsi-mano/WORKFLOWS/ETSI-MANO

COPY docker/msa/license/* /opt/ubiqube/

RUN ln -s /opt/sms/bin/check_services_status.sh /usr/bin/

CMD ["/sbin/init"]

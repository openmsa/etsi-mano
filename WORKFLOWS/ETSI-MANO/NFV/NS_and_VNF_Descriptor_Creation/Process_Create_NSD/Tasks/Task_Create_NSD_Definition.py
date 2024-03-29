import json
import uuid
import os
import errno
import sys
import base64
from msa_sdk import constants
from msa_sdk.order import Order
from msa_sdk.variables import Variables
from msa_sdk.msa_api import MSA_API

dev_var = Variables()
dev_var.add('nsd_name', var_type='String')
dev_var.add('nsd_contents', var_type='String')
context = Variables.task_call(dev_var)

#get uuid from context.
uuid_gen = context.get('uuid_gen')

nsd_name = uuid_gen
if 'nsd_name' in context:
    name = context.get('nsd_name')
    if name:
        nsd_name = name + '_' + uuid_gen

filename = '/opt/fmc_repository/Datafiles/NFV/NSD/' + nsd_name + '/Definition/' + nsd_name + '.yaml'

#get VNFD contents from input variable.
nsd_contents = ''
if 'nsd_contents' in context:
    nsd_contents = context.get('nsd_contents')

#create file in http server directory.
if not os.path.exists(os.path.dirname(filename)):
    try:
        os.makedirs(os.path.dirname(filename))
    except OSError as exc: # Guard against race condition
        if exc.errno != errno.EEXIST:
            raise

nsd_contents_base64 = base64.b64decode(nsd_contents)
nsd_contents_base64_message = nsd_contents_base64.decode('ascii')

with open(filename, "w") as file:
    file.write(nsd_contents_base64_message)
    file.close()

#unset nsd_contents variable value to avoid out of boxing service instance details diplaying in the UI.
context.update(nsd_contents="")

MSA_API.task_success('NSD TOSCA Sol001 meta was created successfully.', context, True)
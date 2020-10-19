from keystoneclient.auth.identity import v3
from keystoneclient import session
from keystoneclient.v3 import client as keystoneapi
from novaclient import client as novapi
def create(name1):
    auth_url = 'http://10.37.129.10:5000/v3'
    username = 'admin'
    user_domain_name = 'Default'
    project_name = 'admin'
    project_domain_name = 'Default'
    password = 'admin'
    auth = v3.Password(auth_url=auth_url,
                       username=username,
                       password=password,
                       project_name=project_name,
                       project_domain_name=project_domain_name,
                       user_domain_name=user_domain_name)
    sess = session.Session(auth=auth)
    keystone = keystoneapi.Client(session=sess)
    # print keystone.projects.list()
    nova = novapi.Client(2, session=keystone.session)
    # print nova.glance.list()
    image = nova.glance.find_image('CentOS7')
    # print nova.flavors.list()
    flavor = nova.flavors.find(name='m1.medium')
    # print  nova.neutron.list()
    network = nova.neutron.find_network('test')
    #name1="vm_api"
    server = nova.servers.create(name=name1, image=image, flavor=flavor, nics=[{'net-id': network.id}])
    #return server


f = open("Vmnum.txt","r")
b = f.read()
f.close()
c = int(b)
i = 0
while(i<c):
    name = "vm"+str(i)
    create(name)
    i=i+1
    #print server
#create("vn")
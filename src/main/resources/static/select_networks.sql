select c.name, sn.network, sn.link
from contactbase.contacts c, contactbase.social_networks sn
where c.id = ? and sn.contact_id = ?
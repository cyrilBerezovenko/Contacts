select c.name, em.email
from contactbase.contacts c, contactbase.emails em
where c.id = ? and em.contact_id = ?
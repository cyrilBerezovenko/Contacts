select c.name, ph.phone
from contactbase.contacts c, contactbase.phones ph
where c.id = ? and ph.contact_id = ?
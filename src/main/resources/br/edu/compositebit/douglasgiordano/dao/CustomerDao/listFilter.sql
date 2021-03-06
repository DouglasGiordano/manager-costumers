SELECT customer.* FROM customer
INNER JOIN address ON address.customer = customer.id
WHERE (:name IS NULL OR name like concat('%',:name,'%')) AND (:birthDate IS NULL OR birthDate like :birthDate) AND (:state IS NULL OR state = :state) AND (:city IS NULL OR city like concat('%',:city,'%'))
ORDER BY :sort :sortOrder;

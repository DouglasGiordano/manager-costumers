UPDATE address
SET state =:state, city =:city, neighborhood =:neighborhood, zipCode =:zipCode, street =:street, number =:number,
    additionalInformation =:additionalInformation, main =:main, costumer =:costumer
WHERE id =:id
INSERT INTO role(designation) VALUES ("Client"),("Ouvrier"),("Administrateur");

INSERT INTO user (pseudo, password, role_id) Values ("john@doe.fr","$2y$10$8LGDM.KaKe4VDiKU8Zp9eOnLzqo.KTmxlskAaJVnnf47yfFSJBVUi" ,2),
                                                 ("jane@doe.fr", "$2y$10$8LGDM.KaKe4VDiKU8Zp9eOnLzqo.KTmxlskAaJVnnf47yfFSJBVUi",3),
                                                    ("jean@doe.fr", "$2y$10$8LGDM.KaKe4VDiKU8Zp9eOnLzqo.KTmxlskAaJVnnf47yfFSJBVUi",1);
INSERT INTO chantier (nom, adresse) VALUES ("La maison de Nanami", "5 rue du paradis 30000 Malaisie"),("La maison de Inumaki", "6 rue du paradis 30000 Malaisie");
INSERT INTO tache(nom, temps)VALUES ("Fondation",23),("Charpente",50);
INSERT INTO operation(nom, temps, user_id, chantier_id, tache_id) VALUES ("Couler la dalle de beton", "2024-03-10", 1, 1, 1),("Mettre la charpente", "2024-03-22", 1, 1, 2);


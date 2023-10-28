--Introduir 4 usuaris (sense foto)
INSERT INTO USUARI (LOGIN,PWD,EMAIL) VALUES ('jborras2', 'pwd12345', 'jborras2@milaifontanals.org');
INSERT INTO USUARI (LOGIN,PWD,EMAIL) VALUES ('eborras', 'pwd678900', 'eborras@milaifontanals.org');
INSERT INTO USUARI (LOGIN,PWD,EMAIL) VALUES ('cgomez', 'pwd101010', 'cgomez@milaifontanals.org');
INSERT INTO USUARI (LOGIN,PWD,EMAIL) VALUES ('dsegura', 'pwd2522', 'dsegura@milaifontanals.org');

--1 dels usuaris hagi pujat 2 rutes
INSERT INTO RUTA (TITOL,DESC_RUTA,TEXT_RUTA,DIST,TEMPS,DESN_P,DESN_N,DIFIC,LOGIN_USUARI) VALUES 
                        ('Els Esgavellats',
                        'Una curiosa ruta que parteix d’aquest pàrquing, situat a Vilanova del Camí. El primer tram transcorre per una pista de terra 
                        sense dificultat, seguint les marques grogues i blanques. Més endavant entrarem a les escletxes i seguirem més marques 
                        blanques (trobarem punts als arbres). En principi no hi ha dificultat, però s’ha d’anar molt alerta amb gossos i/o nens/es 
                        petits, sobretot en el tram de les escales.',
                        'Una curiosa ruta que parteix d’aquest pàrquing, situat a Vilanova del Camí. El primer tram transcorre per una pista de terra 
                        sense dificultat, seguint les marques grogues i blanques. Més endavant entrarem a les escletxes i seguirem més marques 
                        blanques (trobarem punts als arbres). En principi no hi ha dificultat, però s’ha d’anar molt alerta amb gossos i/o nens/es 
                        petits, sobretot en el tram de les escales.',
                        6.08, 129, 261, 261, 2, 'eborras');                     
INSERT INTO RUTA (TITOL,DESC_RUTA,TEXT_RUTA,DIST,TEMPS,DESN_P,DESN_N,DIFIC,LOGIN_USUARI) VALUES 
                        ('Mirador de Montserrat',
                        'Sortim de la llar d’infants la Rosella i agafem el camí que hi ha a la dreta el qual ens portarà al carrer Parlament de 
                        Catalunya, el seguim a l’esquerre i continuem tot recte fins a travessar la carretera i trobar-nos amb un carril bici asfaltat
                        en vermell que hem de continuar fins a arribar al mirador de Montserrat.',
                        'Sortim de la llar d’infants la Rosella i agafem el camí que hi ha a la dreta el qual ens portarà al carrer Parlament de 
                        Catalunya, el seguim a l’esquerre i continuem tot recte fins a travessar la carretera i trobar-nos amb un carril bici asfaltat
                        en vermell que hem de continuar fins a arribar al mirador de Montserrat.',
                        1.9, 45, 38, 0, 1, 'eborras');

--1 dels usuaris hagi pujat 1 ruta
INSERT INTO RUTA (TITOL,DESC_RUTA,TEXT_RUTA,DIST,TEMPS,DESN_P,DESN_N,DIFIC,LOGIN_USUARI) VALUES 
                        ('Igualada-Carme',
                        'Carme és un municipi de la comarca de l''Anoia, situat a la província de Barcelona, comunitat autònoma de Catalunya, Espanya.
                        Concretament, el poble es troba al bell mig d''una vall, que està composta per una banda per la serra d''Orpinell (751 m) i de
                        l''altra per la de Collbàs (544 m). El poble es troba ancorat al fons de la vall, al mig del bosc i subtilment apartat de les 
                        grans poblacions. La capital de la comarca de l''Anoia, Igualada, es troba exactament a deu quilòmetres del mateix poble, 
                        mentre que altres municipis com la Pobla de Claramunt o Capellades es troben escassament a uns cinc quilòmetres de la vila. 
                        Actualment el poble compta aproximadament amb uns 800 habitants, però en temporades de vacances aquesta xifra es pot veure 
                        lleugerament augmentada',
                        'Carme és un municipi de la comarca de l''Anoia, situat a la província de Barcelona, comunitat autònoma de Catalunya, Espanya.
                        Concretament, el poble es troba al bell mig d''una vall, que està composta per una banda per la serra d''Orpinell (751 m) i de
                        l''altra per la de Collbàs (544 m). El poble es troba ancorat al fons de la vall, al mig del bosc i subtilment apartat de les 
                        grans poblacions. La capital de la comarca de l''Anoia, Igualada, es troba exactament a deu quilòmetres del mateix poble, 
                        mentre que altres municipis com la Pobla de Claramunt o Capellades es troben escassament a uns cinc quilòmetres de la vila. 
                        Actualment el poble compta aproximadament amb uns 800 habitants, però en temporades de vacances aquesta xifra es pot veure 
                        lleugerament augmentada',
                        39.58, 340, 773, 773, 5, 'jborras2');

INSERT INTO FETES VALUES ('eborras', TO_DATE('2023-08-28 11:10','yyyy/mm/dd hh24:mi'),1);
INSERT INTO FETES VALUES ('eborras', TO_DATE('2023-09-02 18:19','yyyy/mm/dd hh24:mi'),2);
INSERT INTO FETES VALUES ('jborras2', TO_DATE('2023-08-05 17:06','yyyy/mm/dd hh24:mi'),3);
INSERT INTO FETES VALUES ('eborras', TO_DATE('2023-09-23 09:35','yyyy/mm/dd hh24:mi'),3);
INSERT INTO FETES VALUES ('dsegura', TO_DATE('2023-09-23 09:35','yyyy/mm/dd hh24:mi'),3);


--Introduir 10 tipus de punts (per exemple cim, llac, riu, cascada, font, cova, trencall, càmping, aparcament, metro, autobús,...)
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Waypoint','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Avituallament','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Font','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Intersecció','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Aparcament','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Vall','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Cim','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Llac','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Riu','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Cascada','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Cova','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Metro','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Autobús','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Escales','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Poliesportiu','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Llar d''infants','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Mirador','15882');
INSERT INTO TIPUS (NOM,ICONA) VALUES ('Bicicletes','15882');

--Cada ruta ha de tenir, com a mínim, 5 punts de diversos tipus
--Ruta 1
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (1,'Pàrquing','Petita zona per aparcar el cotxe',501,201,282,1,5);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (2,'Trencall-Esquerra','Trobarem un cartell on girarem cap a l''esquerra',504,205,301,1,4);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (3,'Zona pícnic','Destinada al lleure amb la família o amics',550,215,347,1,2);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (4,'Per baix','S''ha de baixar el desnivell i tombar cantonada vora l''arbre arran del camí',565,230,382,1,6);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (5,'Creuem','Creuar per sota el pont',579,286,403,1,4);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (6,'Marca blanca','En aquesta zona haurem d’anar seguint les marques blanques (punts) que trobarem als arbres',581,294,484,1,1);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (7,'Escales','Pujar els dos graons per arribar al destí',599,299,523,1,14);

--Ruta 2
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (1,'La Rosella','Llar pels més petits',272,273,353,2,16);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (2,'Tombar cap a la dreta','Desviament per agafar el carrer el qual ens portarà al següent punt',275,278,363,2,4);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (3,'Anar recte','Caminar tot recte fins trobar la següent indicació',277,281,368,2,1);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (4,'Carril bici asfaltat','Carril bici en bon estat de grava color vermell',279,284,375,2,18);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (5,'Mirador de Montserrat','Últim punt del camí en què es pot veure tota la muntanya',284,287,399,2,17);

--Ruta 3
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (1,'Can Titó','Poliesportiu del poble per a la iniciació de diferents esports',129,400,279,3,15);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (2,'Rest. Marcet','Per repostar energies',137,417,303,3,2);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (3,'Mare de Déu de Collbàs','Petita font per hidratar-se durant la caminada',145,444,317,3,3);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (4,'Corriol 10º','Primer indicador a seguir',161,465,333,3,1);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (5,'Corriol 15º','Segon indicador de ruta a seguir',169,477,351,3,1);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (6,'Corriol 19º','Darrer indicador de ruta, proper al final',179,486,419,3,1);
INSERT INTO PUNT (NUM,NOM,DESC_PUNT,LAT,LON,ALT,ID_RUTA,ID_TIPUS) VALUES (7,'Dipòsits Vilanova','Cim de la ruta amb bones vistes',171,480,467,3,7);

--1 ruta tingui 2 comentaris
--1 comentari indicant que no ha fet la ruta.
INSERT INTO COMENTARI (TEXT,V_INF,FETA,LOGIN_USUARI,ID_RUTA) VALUES ('Encara no he tingut l''oportunitat de poder completar-la!',5,0,'cgomez',3);

--1 comentari indicant que ha fet la ruta, fent valoració total i incorporant a 2 companys de ruta.
INSERT INTO COMENTARI (TEXT,V_INF,FETA,V_SEG,V_PAI,DIFIC,LOGIN_USUARI,ID_RUTA) VALUES ('M''ha agradat molt aquesta ruta però, algunes indicacions resulten confuses',4,1,4,5,2,'eborras',3);
INSERT INTO COMPANYS VALUES ('dsegura',2);


COMMIT;

--Comprovar es possible modificar el comentari en el què consta que s’ha fet la ruta i que la modificació provoca automàticament que els camps 
--v_seg, v_pai i dific passin a no tenir valor i desapareguin els companys de ruta.
UPDATE COMENTARI SET FETA = 0 WHERE ID = 2;

--Incorporar companys de ruta en un comentari que no indica que s’ha fet la ruta.
INSERT INTO COMPANYS VALUES ('cgomez',2);

--Incorporar en un comentari com a company de ruta a l’usuari del comentari.
INSERT INTO COMPANYS VALUES ('eborras',2);

--Que l’usuari propietari d’una ruta en faci un comentari.
INSERT INTO COMENTARI (TEXT,V_INF,FETA,V_SEG,V_PAI,DIFIC,LOGIN_USUARI,ID_RUTA) VALUES ('Es tracta d''una ruta molt senzilla de realitzar',5,1,5,5,1,'eborras',1);

--Introduir comentari sense marcar que s’ha fet la ruta i amb valor en algun dels camps v_seg, v_pai o dific.
INSERT INTO COMENTARI (TEXT,V_INF,FETA,V_SEG,V_PAI,DIFIC,LOGIN_USUARI,ID_RUTA) VALUES ('No he fet la ruta però voldria anar-hi quan pugui',5,0,4,4,3,'cgomez',3);

ROLLBACK;
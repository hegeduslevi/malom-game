#Malom játék

Ez a project malomjáték JAVA implementációját tartalmazza.

A program JDBC kapcsolaton keresztül csatlakozik egy SQL adatbázishoz ahol nyilvántartja a játékosi statisztikákat.
Üres adatbázis esetére a könyebb tesztelés érdekében XML állományból lehet játékosi statisztikákat az adatbázisba táplálni.

A kezelőfelület használata:

	- Bal egérgombbal lehet mozgatni a köveket
	- Jobb egérgombbal lehet lehelyezni követ a táblára 
	- A kör befejezését a következő gomb lenyomásával lehet kezdeményezni.
	- Felad gomb megnyomásakor az aktuális játékos vesztesként az ellenfél győztesként lesz elszámolva
	- Az adatb-t inicializál gomb hatására az XMl állományból feltöltődnek az adatok az adatbázisba
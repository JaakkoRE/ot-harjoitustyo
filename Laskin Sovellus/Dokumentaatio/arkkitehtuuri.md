
# Arkkitehtuurikuvaus
## Rakenne
Pakkausrakenne on seuraava:
<img src="https://user-images.githubusercontent.com/62024790/79998265-286de000-84c3-11ea-8ddc-70dda3ba12e6.png" width="1000"> <br>
Pakkaus calculatorprogram.gui sisältää graafisen java fx tyylisen käyttöliittymän koodin. 

Pakkaus calculatorprogram.calculators sisältää laskinohjelmien sovelluslogiikan.

Pakkaus calculatorprogram.database sisältää tietokannan sovelluslogiikan.

## Käyttöliittymä
Käyttöliittymä on toteutettu calculatorprogram.gui paketin sisällä olevassa luokassa UserInterface. Näkymien välillä on muutama yhtenäinen nappi kuten takaisin menuun. mutta käyttöliittymä kooskuu menun lisäksi 3 päänäkymästä, jotka ovat laskin-, graafinen laskin- ja tietokantanäkymä. Käyttöliittymä kutsuu pakettien calculatorprogram.calculators ja calculatorprogram.database metodeja. Graafinen laskin käyttää javan LineChart oliota piirtämään annetusta ArrayLististä kuvaajia 

## Sovelluslogiikka
Pakettikaavio/logiikka
<img src="https://user-images.githubusercontent.com/62024790/80286338-915b8f00-8733-11ea-9aeb-24a0ab803255.png"> <br>
Calculator luokka tarjoaa eri lasku metodit kuten 
- calcArrayList(String text),  muuttaa String tyyppisen olion laskettavaan metodiin, lähettää laskun eteenpäin ja palauttaa tuloksen
- calculate(List<String> calculatableArrayList), laskee edellisen metodin luoman tyylisen ArrayListin ja palauttaa tuloksen  
- binomial(int n, int k),  laskee kombinaatioden määrän ja palauttaa arvon
- permutational(int n, int k),  laskee permutaatioden määrän ja palauttaa arvon
  
Graafinen laskin hajottaa laskun itse ja sijottelee x.än arvot ja syöttää tämän funktion Calculator luokan calculatable metodille ja lisää eri x.än syötetyillä arvoilla saadut tulokset ArrayListiin jonka se palauttaa lopuksi. 

Graafinen laskin ja perus laskin kutsuvat calculatorprogram.database paketin database luokan metodia getValue jolla ne muuttavat nimetyt arvot jotka on syötetty tietokantaan vakioiksi.

Database tarjoaa eri tietokannan käsittely metodit kuten
- creation(), luo tietokannan
- addValueToDatabase(String name, double value) lisää arvon nimellä tietokantaan.
- getValue(String name) palauttaa vakion nimen perusteella
- deleteValue(String name) poistaa vakion nimen perusteella
- addPassword(String password) lisää salasanan

Jos salasana on tietokannassa ja käyttäjä ei ole syöttänyt salasanaa tietokannalle, arvoja ei voi lisätä, poistaa tai hakea tietokannasta.
## Tietojen pysyväistallennus
calculatorprogram.database paketin database luokka käyttää sql tyylistä tietokantaa säilyttääkseen tietoja.

Database luokkassa on kaikki metodit tietojen lisäämiseen,poistamiseen,hakemiseen,salasanan muuttamiseen ja poistamiseen jotka käyttävät eri sql komentoja.

sql tietokannassa on 2 taulua. Ensimmäisessä taulussa on salasana taulu jossa säilytetään tieto salasanasta ensimmäisellä rivillä ja toisella siittä onko käyttäjä syöttänyt salasanan istunnon aikana. Toisessa taulussa arvot ovat kytketty nimeen.

Esimerkki salasana taulusta
<pre>
1 Salasana12345   
2 true
</pre>
Esimerkki arvo taulusta
<pre>
1 g        9.81
2 hs       550
3 molVolum 22.41383
</pre>

## Päätoiminnallisuudet
### Miten graafinen laskin laskee funktion
Kun graafisessa näkymässä on annettu vaadittavat tiedot laskusta (calculatable), x.n ylärajan (upperBound), x.n alarajan (lowerBound), x.n arvojen välin (precision) Esimerkissä on annettu 5x^2,3,2,1
<img src="https://user-images.githubusercontent.com/62024790/80003742-1e9bab00-84ca-11ea-9eb2-f3ec748f95ae.png">

Laskun suorituksen jälkeen käyttöliittymä saa listan välin jokaisesta arvosta, tässä tapauksessa vain 20 ja 45 ja piirtää niiden perusteella kuvaajan.
### Miten tietokannasta haetaan tietoa
Tietokannassa on valmiiksi arvo 9.81 nimellä g ja salasana on annettu. Sekvenssikaavio kuvaa miten laskettavassa tekstissä oleva vakion nimi muunnetaan vakioksi
<img src="https://user-images.githubusercontent.com/62024790/80286304-46417c00-8733-11ea-92be-b5d0e8ad9715.png">

Kun vakio on saatu tietokannasta, lasku jatketaan loppuun.


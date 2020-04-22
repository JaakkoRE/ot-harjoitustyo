
# Arkkitehtuurikuvaus
## Rakenne
Pakkausrakenne on seuraava:
<img src="https://user-images.githubusercontent.com/62024790/79998265-286de000-84c3-11ea-8ddc-70dda3ba12e6.png" width="1000"> <br>
Pakkaus calculatorprogram.gui sisältää graafisen java fx tyylisen käyttöliittymän koodin, calculatorprogram.calculators sisältää laskinohjelmien sovelluslogiikan ja calculatorprogram.database sisältää tietokannan sovelluslogiikan

## Käyttöliittymä
Käyttöliittymä on toteutettu calculatorprogram.gui paketin sisällä olevassa luokassa UserInterface. Näkymien välillä on muutama yhtenäinen nappi kuten takaisin menuun. mutta käyttöliittymä kooskuu menun lisäksi 3 päänäkymästä, jotka ovat laskin-, graafinen laskin- ja tietokantanäkymä. Käyttöliittymä kutsuu paketin calculatorprogram.calculators ja calculatorprogram.database metodeja. Graafinen laskin käyttää javan LineChart oliota piirtämään annetusta ArrayLististä kuvaajia 

## Sovelluslogiikka
Pakettikaavio/logiikka
<img src="https://user-images.githubusercontent.com/62024790/79855819-25e08d00-83d4-11ea-9907-0f4b96360171.png" width="1000"> <br>
Calculator luokka tarjoaa eri lasku metodit kuten calcArrayList joka muuttaa String tyyppisen olion laskettavaan metodiin ja calculate joka laskee tämän tyylisen ArrayListin. Luokka tarjoaa myös permutaatio ja kombinaatio laskumetodit binomial ja permutational metodilla.
Graafinen laskin hajottaa laskun itse ja sijottelee x.än arvot ja syöttää tämän funktion Calculator luokan calculatable metodille.
Graafinen laskin ja perus laskin kutsuvat calculatorprogram.database paketin database luokan metodia getValue jolla ne muuttavat nimetyt arvot jotka on syötetty tietokantaan vakioiksi.
## Tietojen pysyväistallennus
calculatorprogram.database paketin database luokka käyttää sql tyylistä tietokantaa säilyttääkseen tietoja. <br>
sql tietokannassa on 2 taulua. Toinen on salasana taulu jossa säilytetään tieto salasanasta ensimmäisellä rivillä ja toisella siittä onko käyttäjä syöttänyt salasanan istunnon aikana, ja toinen taulu jossa arvot ovat kytketty nimeen.<br>
Esimerkki salasana taulusta
<pre>
1 Salasana12345   
2 true
</pre>
Esimerkki arvo taulusta
<pre>
1 g 9.81
2 hs 550
3 molVolum 22.41383
</pre>

<img src="https://user-images.githubusercontent.com/62024790/79855819-25e08d00-83d4-11ea-9907-0f4b96360171.png" width="1000"> <br>
## Sekvenssikaavio
Kuvaa miten graafinen laskin laskee 5x^2 välillä 2,3 tarkkudella 1.
<img src="https://user-images.githubusercontent.com/62024790/79855777-14978080-83d4-11ea-8ca7-56aae35ba175.png" width="1000">

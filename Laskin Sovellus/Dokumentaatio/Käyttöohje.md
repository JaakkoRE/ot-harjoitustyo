
# Käyttöohje
lataa tiedosto [CalculatorProgram.jar](https://github.com/mluukkai/OtmTodoApp/releases/tag/0.1)
## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar CalculatorProgram.jar
```
## Aloitusnäkymä
Sovelluksen käynnistyksen jälkeen avautuu seuraava näkymä.

<img src="https://user-images.githubusercontent.com/62024790/80006754-03cb3580-84ce-11ea-9937-8675642d44b3.png" width="400">

Valikosta voi valita halutun toiminnallisuuden.


## Laskin
Kun painaa aloitusnäytöstä laskin nappia avautuu seuraava näkymä.

<img src="https://user-images.githubusercontent.com/62024790/80006760-0463cc00-84ce-11ea-8467-ec5851aea48a.png" width="400">

Kun painaa lisää nappia, vasemmalle ilmestyy eri laskin näkymät ja kun painaa näytä tietokannan arvot nappia, näkyy arvot jotka on syötetty tietokantaan seuraavalla tavalla:.

<img src="https://user-images.githubusercontent.com/62024790/80006761-0463cc00-84ce-11ea-8a92-0a8687f4ce4b.png" width="400">

Laskukenttään voi kirjoittaii näppäimistöllä tai käyttää pikanäppäimiä.

Tietokantaan tallennetut arvot voi saada painamalla lisää arvo nappia tai kirjoittamalla vakion nimen esimerkiksi: 5+g tuottaisi 14.81 arvon. Lasketun arvon voi lisätä tietokantaan painamalla lisää tietokantaan nappia.

<img src="https://user-images.githubusercontent.com/62024790/80007697-4b05f600-84cf-11ea-9ffa-fa7597532f38.png" width="400">

Jos valitsee esimerkiksi Binomi laskin napin avautuu seuraava näkymä jossa voi laskea kombinaatiot.
## Graafinen laskin
Kun painaa aloitusnäytöstä graafinen laskin nappia avautuu seuraava näkymä.

<img src="https://user-images.githubusercontent.com/62024790/80006751-03329f00-84ce-11ea-99ca-ce871c4b72f6.png" width="400">

Kohtaan F(x) kirjoitetaan funktio jossa voi olla muuttujana x. 

Oikealla laidalla on pikanapit joilla voi kirjoittaa teksti kenttiin tekstiä.

Tarkkuus meinaa kuinka tiheästi lasketaan x.n arvoja. Laskin ei käytä taylor menetelmiä joten se on melko hidas vaikeammilla funktiolla joten matalinta tarkkuutta ei suositella.

Alempana näkyy välit. Tähän voi kirjoittaa lasku välin. Tällä voi myös zoomata tarkemmin eri kuvaajan pisteisiin käyttämällä tarkenna väliin nappia.
## Tietokanta
Kun painaa aloitusnäytöstä tietokanta nappia avautuu seuraava näkymä.

<img src="https://user-images.githubusercontent.com/62024790/80006757-03cb3580-84ce-11ea-9d17-52ffd99611f1.png" width="400">

Jos tietokantaa ei ole luotu ja sitä haluaa käyttää pitää painaa luo tietokanta nappia. Tietokantaan voi syöttää arvoja nimi ja arvo kenttien avulla. Arvojen kuuluu olla vakioita ja nimien pitää koostua kirjaimista, mutta ei saa sisältää e,x kirjaimia.
Näytä arvot näkymä näyttää tietokannan arvot. Kun näkymän avaa vakioiden viereen tulee poista nappi jolla voi poistaa yksittäisi vakioita. Jos haluaa poistaa kaiken voi painaa poista kaikki arvot

Jos haluaa lisätä salasanan alhaalla on sille nappi. Jos salasana on laitettu et pääse seuraavalla istunnolla tietokantaan käsiksi ilman syöttämättä salasanaa tietokanta näkymässä. Kun olet lisännyt salasanan, tämän tilalle tulee poista salasana nappi, jolla voi poistaa salasanan.

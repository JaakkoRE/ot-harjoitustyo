
# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla.
## Yksikkö- ja integraatiotestaus
### Laskimien sovelluslogiikka testaus
Laskimen ja graafisenlaskimen perustuvat pääosin integraatiotesteihin ja muutamaan yksikkötestiin. Testauksen hoitavat luokat CalculatorTest ja GraphicCalculatorTest. Laskinten sovelluslogiikan lisäksi CalculationMethodTestissä on luokan CalculationMethods perustuvia yksikkötestejä.
### Tietokannan testaus
Tietokantaa testataan erillisellä DatabaseTest.db tiedostolla. Testit hyödyntävät JUnitin metodeja. Testit ovat suurimmaksi osaksi yksikkötestejä.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 90% ja haarautumakattavuus 77%

<img src="https://raw.githubusercontent.com/JaakkoRE/ot-harjoitustyo/master/Laskin%20Sovellus/Dokumentaatio/Uusi%20kansio/Testauskattavuus.png" width="700">

Testaamatta jäivät tilanteet, joissa käyttäjä syöttää laskukenttään nimellä tietokannan arvon ja tilanteet joissa tietokantaa ei ole olemassa.

## Manuaalinen testaus
### Toiminnallisuudet

[Määrittelydokumentin](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Vaatimusm%C3%A4%C3%A4rittely.md) ja [käyttöohjeen](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/K%C3%A4ytt%C3%B6ohje.md) listaamat toiminnallisuudet on käyty läpi. On pyritty testaamaan mahdollisimman laajasti erilaisilla toimivilla ja virheellisillä syötteillä.


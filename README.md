# Ohjelmistotekniikka harjoitustyö, calculator program
Sovelluksen tarkoitus on hoitaa perus graafisia ja ei graafisia laskutoimituksia.


## Dokumentaatio
[Käyttöohjeet](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/K%C3%A4ytt%C3%B6ohje.md) <br>
[Työaikakirjanpito](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Ty%C3%B6aikakirjanpito.md) <br>
[Vaativuusmäärittely](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Vaatimusm%C3%A4%C3%A4rittely.md) <br>
[Arkkitehtuurikuvaus](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/arkkitehtuuri.md)
## Releaset
[Viikko5](https://github.com/JaakkoRE/ot-harjoitustyo/releases/tag/viikko5)
[Viikko6](https://github.com/JaakkoRE/ot-harjoitustyo/releases/tag/Viikko6)
## Komentorivitoiminnot
### Testaus

Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn jacoco:report
```
### Checkstyle

Tiedostoon määrittelemät tarkistukset suoritetaan komennolla
```
 mvn jxr:jxr checkstyle:checkstyle
```

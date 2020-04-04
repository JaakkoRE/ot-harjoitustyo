# Ohjelmistotekniikka, Harjoitustyö



## Dokumentaatio
[Työaikakirjanpito](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Ty%C3%B6aikakirjanpito) <br>
[Vaativuusmäärittely](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Vaatimusm%C3%A4%C3%A4rittely.md) <br>
[Arkkitehtuurikuvaus](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/arkkitehtuuri.md)
## Releaset
[Viikko4](https://github.com/JaakkoRE/ot-harjoitustyo/tree/master/Laskin%20Sovellus)
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

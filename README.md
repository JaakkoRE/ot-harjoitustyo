# Ohjelmistotekniikka harjoitustyö, calculator program
Sovelluksen tarkoitus on hoitaa perus graafisia ja ei graafisia laskutoimituksia.


## Dokumentaatio
[Käyttöohjeet](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/K%C3%A4ytt%C3%B6ohje.md)

[Työaikakirjanpito](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Ty%C3%B6aikakirjanpito.md) 

[Vaativuusmäärittely](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Vaatimusm%C3%A4%C3%A4rittely.md) 

[Arkkitehtuurikuvaus](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/JaakkoRE/ot-harjoitustyo/blob/master/Laskin%20Sovellus/Dokumentaatio/Testaus.md)

## Releaset
[Viikko5](https://github.com/JaakkoRE/ot-harjoitustyo/releases/tag/viikko5) <br>
[Viikko6](https://github.com/JaakkoRE/ot-harjoitustyo/releases/tag/Viikko6)
[Loppupalautus](https://github.com/JaakkoRE/ot-harjoitustyo/releases/tag/loppupalautus) 
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
### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston. 

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_
### Checkstyle

Tiedostoon määrittelemät tarkistukset suoritetaan komennolla
```
 mvn jxr:jxr checkstyle:checkstyle
```

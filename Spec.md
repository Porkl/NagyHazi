# Specializáció - WireWorld

A WireWorld egy többszínű sejtautomata, amivel szimulálni lehet kábeleken futó elektromosságot.
Főleg tranzisztorok modellezésére használható, és ezen kívül Turing-teljes rendszert épít fel.
Logikai kapuk úgyszintén megvalósíthatóak vele.

# Szabályok

A járéktér egy tábla, amelyen négyzetek helyezkednek el, ezek a cellák.
Minden cellának 4 állapota lehet, amelyekhez mindegyikhez színeket is rendelünk:

    Üres (Fekete)
    Elektron Fej (Kék)
    Elektron Farok (Piros)
    Vezető (Sárga)

Mint minden sejtautomatában, az idő tellését "tick"-ekben mérjük, amikben a következő állapotváltozások történhetnek a cellákkal:

    Üres -> Üres
    Elektron Fej -> Elektron Farok
    Elektron Farok -> Vezető
    Vezető -> Elektron Fej, Vezető
    (Ha pontosan egy vagy kettő a körülhatároló cellából Elektron Fej, akkor az adott cella is Elektron Fej lesz, egyébként marad Vezető.)

# Főbb funkciók és elérésük

A program egy menürendszerrel fogadja a felhasználót a program indításánál.

## Menüszerkezet

- ***New WireWorld Map***: 
  - Új felület indítása, amelyen minden cella alapból Üres állapotú.

- ***Load Previous Map***:
  - Az előző játékmenet tartalmának visszaolvasása, majd annak a játékmenetnek a folytatása.
  - Amennyiben még nincs mentett játék, úgy új játékot kezdünk, mintha a ***New WireWorld Map*** menüpontot választottuk volna ki.
  
- ***Settings***:
  - A beállítások menü előhozása, amelyben a beállítható:
    - **Playfield size**: A játéktér *sorainak*, illetve *oszlopainak* száma.
  
- ***Exit the Game***:
  - Kilépünk a játékból, és bezárjuk az ablakot.

## A játék felépítése

Amint a játékba beléptünk, az alábbi lehetőségek állnak rendelkezésünkre:

- A pálya akármelyik cellájára kattintva megváltoztathatjuk az állapotát 

        Az állapotváltozások sorrendje:
        Üres -> Vezető -> Elektron Fej -> Elektron Farok -> Üres -> ...

- Megállíthatjuk a játékot

  - Ekkor az Elektron Fejek nem terjednek tovább, és az Elektron Farkak is ugyanott maradnak.
  
- Elindíthatjuk a játékot
  
  - A játék tovább zajlik "tick"-enként, az Elektron Fejek továbbterjedenk, és Elektron Farkakat hagynak maguk után, az Elektron Farkak pedig eltűnnek.

# Megoldási ötlet

- A játék egy menüszerkezetet tartalmaz, amely a Swing GUI által lesz megvalósítva.

- A játéktér hasonlóan grafikus formában lesz megvalósítva, melyet egérkattintással lehet irányítani, illete változtatni a különböző négyzetekben lévő állapotokat.

- A játékállás, illetve a beállítások külső fájlba menthetőek, azok a játék, illetve a program indulásánál visszaolvashatóak.
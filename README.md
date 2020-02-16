# DAT055-projekt
Projektarbete i kursen DAT055 

# NAMN OCH VERSION (läs innan du gör en branch eller pull request)
- **Klient:** JadaJada-Client-v1.x      ( x = version )

- **Server:** JadaJada-Server-v1.x      ( x = version )

# TODO (14 Februari 2020)
- [ ] Veckorapport för Tisdag (18e Februari)

- [ ] Påbörja rapport / Inlämning (Kolla Canvas vad som står som krav)

- [ ] UML diagram

- [ ] Sekvens diagram

- [x] Flytta kod ifrån konstruktorn till metoder

- [x] Kommentera koden

- [ ] Separera all funktionallitet och GUI

- [ ] Skriv koden mer läsbart

- [ ] Skapa enhetliga variabelnamn och metoder


## JadajadaClient v0.0 <- Erik
- Basversion av client
- All GUI skapad av Erik
- All interaktion med server skapad av Axel


## JadajadaServer v0.0 <- Axel
- Basversion av server
- Skapad av Axel

## JadajadaClient v1.1 <- Axel
- Separerade Gui och Client helt
- Gjort metoder till Lamda actionlisteners 
- Städat i Gui koden. Implementerat interface till både Client & Gui

## JadaJadaClient v1.2 <- Erik
- ADDED: loginscreen, threaded client class
- Main is not configured for launch. Change before starting java aplication

## JadaJadaClient v1.3 <- Erik
- loggin menu fixed further
- action listeners implemented to texfields for userinput
- ENTER button now works 
- Client GUI fixed further
- General hotfixes

## JadaJadaServer v1.0 <- Axel
- Städat lite kod i client-handler
- kickar nu meddelande historik till alla som loggar in.

## JadaJadaClient v1.4 <- Axel
- Skapat en metod som skapar olika teman
- Gjort ett tibia tema
- Implementerat tema combobox i login gui

## JadaJadaServer v1.1 <- Erik
- Created new server GUI
- General hotfixes
- Cleaned upp code
- Interfaced further

## JadaJada-Server-v1.2 <- Erik
- Code cleaned, indentation, comments
- All methodes called "Configuration" are now called "Init" short for initialize, I felt it made the code more intuitive
- Added functionality for "admin" commands, ex. typing "disconnect" terminates the server

## JadaJadaClient v1.5 <- Erik
- Changed window size
- Cleaned code, indentation, comments
- Sepperated functionallity away from constructor to sepperate methods

# DAT055-projekt
Projektarbete i kursen DAT055 

# NAMN OCH VERSION (läs innan du gör en branch eller pull request)
- **Klient:** JadaJada-Client-v2.x      ( x = version )

- **Server:** JadaJada-Server-v2.x      ( x = version )

# TODO (25 Februari)
- [ ] Vecorapport för Tisdag (3e Mars)
- [ ] Kommentera ( /** framför publika metoder och klasse )
- [ ] Flytta servern till DigitalOcean
- [ ] Skriva rapport

# TODO (18 Februari)
- [x] Vecorapport för Tisdag (25e Februari)

- [x] Ändra alla metoder som inte behöver vara public till private

- [x] UML diagram

- [x] Påbörja rapport / dokumentation / inlämning (Kolla canvas vad som står som krav)

- [ ] Testa programmet, få det att fungera utanför Chalmers! Försök att få det att fungera "på riktigt" 

- [ ] Undersök "Edge Cases",  
                  
                    vad händer om någon försöker chatta när servern är stängd?
                
                    vad händer om någon är inloggad när servern stängs?


# TODO (14 Februari)
- [x] Veckorapport för Tisdag (18e Februari)

- [ ] Påbörja rapport / Inlämning (Kolla Canvas vad som står som krav)

- [ ] UML diagram

- [ ] Sekvens Diagram

- [x] Flytta kod ifrån konstruktorn till metoder

- [x] Kommentera koden

- [x] Separera all funktionallitet och GUI

- [x] Skriv koden mer läsbart

- [x] Skapa enhetliga variabelnamn och metoder


# Client updates

## JadaJada-Client-v2.0 <- Erik
- First complete working version

## JadaJadaClient v1.5 <- Erik
- Changed window size
- Cleaned code, indentation, comments
- Sepperated functionallity away from constructor to sepperate methods


## JadaJadaClient v1.4 <- Axel
- Skapat en metod som skapar olika teman
- Gjort ett tibia tema
- Implementerat tema combobox i login gui


## JadaJadaClient v1.3 <- Erik
- loggin menu fixed further
- action listeners implemented to texfields for userinput
- ENTER button now works 
- Client GUI fixed further
- General hotfixes


## JadaJadaClient v1.2 <- Erik
- ADDED: loginscreen, threaded client class
- Main is not configured for launch. Change before starting java aplication


## JadajadaClient v1.1 <- Axel
- Separerade Gui och Client helt
- Gjort metoder till Lamda actionlisteners 
- Städat i Gui koden. Implementerat interface till både Client & Gui


## JadajadaClient v0.0 <- Erik
- Basversion av client
- All GUI skapad av Erik
- All interaktion med server skapad av Axel


# Server updates

## JadaJada-Server-v2.1 <- Erik
- Added scrollbar
- Added errormessage

## JadaJada-Server-v2.0 <- Erik
- First complete project  version
- Added further admin controlls
- /tellAll <msg> sends message to all loggedin clients
- /latencySLOW,NORMAL,FAST sets the server latency speed

## JadaJada-Server-v1.2 <- Erik
- Code cleaned, indentation, comments
- All methodes called "Configuration" are now called "Init" short for initialize, I felt it made the code more intuitive
- Added functionality for "admin" commands, ex. typing "disconnect" terminates the server


## JadaJadaServer v1.1 <- Erik
- Created new server GUI
- General hotfixes
- Cleaned upp code
- Interfaced further


## JadaJadaServer v1.0 <- Axel
- Städat lite kod i client-handler
- kickar nu meddelande historik till alla som loggar in.


## JadajadaServer v0.0 <- Axel
- Basversion av server
- Skapad av Axel


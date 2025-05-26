This is my exam in "Object Oriented Programming" using Java. Below you will find the exam-task:

# Oppgaven

Caset omhandler funn av historiske gjenstander. En gruppe hobby-arkeologer har samlet historiske gjenstander over en periode.

Informasjon om hvem som har funnet hvilke gjenstander er samlet i en tekstfil (funn txt). Filen inneholder også informasjon om museer der noen av gjenstandene befinner seg. For en beskrivelse av filen, se vedlegg 1.

## Del 1 - importere data til database:

Du skal lage et program som leser fra filen funn.txt og legger dataene inn i en database.

Et forslag til databasestruktur finner du i filen funn.sql. Du kan velge å gjøre justeringer i funn.sql, men det skal ikke være nødvendig.

Når du er ferdig med del 1 bør du ha:

- En velfungerende database som har informasjon om funn av historiske gjenstander.
  Tabellene i databasen er fylt med data basert på filen funn.txt.
- En eller flere klasser som kan kommunisere med databasen.
- En eller flere klasser som kan holde i data fra databasen. Det er forventet at arv benyttes for klasser som omfatter ulike typer funn ettersom de har mange felles egenskaper.

OBS! Husk at eksamenstiden er begrenset. Hvis du ikke klarer å komme i mål med del 1 innen rimelig tid, så er du nødt til å gå videre til del 2. Ta i så fall vare på koden du skrev for del 1, slik at den kan bli vurdert.

## Del 2 – bruke databasen:

Du skal lage et lite program som tilbyr brukeren en meny med noen menyvalg som omhandler de historiske gjenstandene som nå er plassert i databasen (etter at del 1 er gjennomført).

Du kan selv velge om du vil ha to main-metoder (en for del 1 og en for del 2), eller om du vil kommentere ut koden for del 1 når du utvikler del 2. Eller du kan velge å ha del 1 og del 2 i samme program.

Programmet skal tilby følgende funksjonalitet:

- Se informasjon om alle funngjenstander.
- Se informasjon om alle funngjenstander eldre enn <årstall>. (Brukeren angir årstall).
- Få informasjon om antall funngjenstander registrert.
- Avslutte programmet.

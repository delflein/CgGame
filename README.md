# Monopoly
Computergrafik Uniprojekt

Hier ist unsere Implementierung des Brettspielklassikers Monopoly.
Das Spiel wurde mithilfe des LibGDX Frameworks entwickelt und nutzt Gradle 
zum Dependencymanagement.

Die Speicherung der Settings ist über das Preferences System von Java implementiert.
Die Dateien werden im USERDIR/.prefs/Monopoly Ordner angelegt.

Assets aus Fremdquellen ist in der Datei assets.txt aufgeführt.
Nicht dort aufgeführte Assets wurden von uns erstellt.

### Installation
Das Spiel setzt zwingend eine gültige Java8 Installation vorraus.
Weitere Dependencies liegen der .jar bei, mit der das Spiel einfach 
gestartet werden kann.


### Ziel des Spiels
Ziel des Spiels ist es, als letzter Spieler übrig zu bleiben.
Ein SPieler scheidet aus, wenn sein Kontostand unter null fällt und er seine
Runde beendet.


### Spielablauf
Der Spielablauf ist gemäß den Standardregeln von Monopoly.
In unserer Implementation ist ein Rundensystem mit dem folgendem Ablauf:
- Würfeln
- Aktion gemäß des erreichten Feldes
- wenn Pasch, Würfeln
- Bauphase/Inspektion des Spielfeldes
- Zugende


### Steuerung
Die Steuerung erfolgt ausschließlich über die Maus. 
Die Würfel sind klickbar in der rechten unteren Ecke des Spielbildschirms.
Andere Aktionen, wie das Kaufen von Grundstücken,
sind mit Buttons in entsprechen Dialogen ausführbar.
Der Besitz der Grundstücke, sowie die Kontostände sind im oberen Bereich des Spielbildschirms zu finden.
Dort sind sind sämtliche Grundstückselemente, die Spieler besitzen interargierbar gestaltet,
um Gebäude zu bauen bzw. Kosten zu prüfen.
In der linken unteren Ecke ist der Button zum Beenden des eigenen Zugs.

### Debug Optionen
Zu Testzwecken sind noch einige Debugfunktionen mit Keyboardtasten belegt.
Diese lassen sich bei Bedarf in der GameInput Klasse ändern/entfernen.
Ebenfalls ist die SelectableComponent Mechanik aktiviert, mit der sich per Mausklick  Entities des Spielbretts
zu selektieren und zu bewegen. 

Folgende Funktionen sind zu diesem Stand verfügbar

Pfeiltasten: Bewegt die Entity in der Spielszene
- Z,U: Rotation der Entit
- C: Ausgabe der aktuellen Kameraposition mit System.out
- M: Verringert das Geld des aktuellen Spielers um 1000
- B: Fügt dem Spieler alle Straßen hinzu
- N: Bewegt den aktuellen Spieler auf LOS
- ESCAPE: Beendet das Spiel
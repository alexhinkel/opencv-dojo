# Coding Dojo by LV 1871: Computer Vision - Object detection

## Voraussetzung

Folgende Software ist notwendig (außerhalb der Citrix Umgebung):

* Windows System (Skripte zum trainieren der Bilderkennung sind für Windows optimiert)
* IntelliJ
* Java 11 JDK
* Chrome Browser (Browsersteuerung mit Selenium für Chrome vorbereitet)
* Kamerazugriff (optional)

### Inhalt:

* Kamera mit Augenerkennung
    * EyeDetection.java -> Kann über Main-Methode gestartet werden
* Moorhuhn Bot (Spielt Moorhuhn automatisch im Chrome Browser)
    * Moorhuhn.java -> Kann über Main-Methode gestartet werden

### Aufgaben/Ideen:

* Einen eigenen Bot entwickeln
* Sich mithilfe der Augenerkennung eine virtuelle Brille aufsetzen
* Den Moorhuhn Bot verbessern
* Demos online ausprobieren
* (!schwierig!) Teamsfilter nachbauen (Profil erkennen und Hintergrund verschwimmen lassen)

### Bildbearbeitung

Mithilfe der Klasse ImgProc können Bilder bearbeitet werden (Text einfügen, Formen einfügen, Schwarz/Weis-Filter, usw.)

### Anleitung: Eigenes Bilderkennungs Modell trainieren

! Beim ausführen jeder Batch-Datei und von GenerateTrainingDataHelper.java den `MODEL_NAME`-Parameter innerhalb der
Datei prüfen !

1. `./opencv_trainer/00_create_new_model.bat` Erstellt Verzeichnishierarchie für neues Modell
2. GenerateTrainingDataHelper.java starten und Traningsdaten erstellen:
    * Drücke 'p' wenn im angezeigten Bild ein Objekt enthalten ist, dass von der Bilderkennung erkannt werden soll (
      positive Trainingsdaten)
    * Drücke 'n' wenn im angezeigten Bild **kein** Objekt enthalten ist, dass von der Bilderkennung erkannt werden
      soll (negative Trainingsdaten)
    * Drücke 'e' um zu beenden
3. `./opencv_trainer/01_start_opencv_annotation.bat` Markierung der Objekte in den positiv Bildern
4. `./opencv_trainer/02_start_opencv_createsamples.bat` Erstellt binäre Eingabedatei für den letzten Schritt, das
   eigentliche Training. Parameter innerhalb der Datei prüfen.
5. `./opencv_trainer/03_start_opencv_traincascade.bat` Startet das Training. Parameter innerhalb der Datei prüfen.

### Nützliche Links:

* [OpenCV](https://opencv.org)
* [Cascade Classifier](https://medium.com/swlh/haar-cascade-classifiers-in-opencv-explained-visually-f608086fc42c)
* [Tensor Flow Demos](https://www.tensorflow.org/js/demos)
* [Datasets](https://cocodataset.org/#home)
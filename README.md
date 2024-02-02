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
* Moorhuhn Bot (Spielt Moorhuhn automatisch im Chrome Browser)

### Anleitung: Eigenes Bilderkennungs Modell trainieren

! Beim ausführen jeder Batch-Datei den `MODEL_NAME`-Parameter innerhalb der Datei prüfen !

1. `./opencv_trainer/00_create_new_model.bat` Erstellt Verzeichnishierarchie für neues Modell
2. `./opencv_trainer/%MODEL_NAME%/pos` Mit Bildern befüllen, die Objekte enthalten, welche von der Bilderkennung
   identifiziert werden sollen. <p>
   `./opencv_trainer/%MODEL_NAME%/neg` Mit Bildern befüllen, die **keine** Objekte enthalten, welche von der
   Bilderkennung identifiziert werden sollen.
3. `./opencv_trainer/01_start_opencv_annotation.bat` Markierung der Objekte in den positiv Bildern
4. `./opencv_trainer/02_start_opencv_createsamples.bat` Erstellt binäre Eingabedatei für den letzten Schritt, das
   eigentliche Training. Parameter innerhalb der Datei prüfen.
5. `./opencv_trainer/03_start_opencv_traincascade.bat` Startet das Training. Parameter innerhalb der Datei prüfen.

### Nützliche Links:

* [OpenCV](https://opencv.org)
* [Cascade Classifier](https://medium.com/swlh/haar-cascade-classifiers-in-opencv-explained-visually-f608086fc42c)
* [Tensor Flow Demos](https://www.tensorflow.org/js/demos)
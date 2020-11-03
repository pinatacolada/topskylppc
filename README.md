# TopSky plugin for Portugal vACC

The TopSky plugin (a.k.a. The Plugin Formerly Known As “EUROCAT 2000 E”) is developed and maintained by Juha Holopainen from VATSIM Scandinavia. It is an almost complete set of tag items, tag menus, graphical elements on the radar display and some additional functionality based around the real world TopSky ATM system. 

As of right now, the aim of this plugin is to provide additional tools for ATC, while creating a system closer to LISATM than TopSky. As such, some tools and safety nets like PDC, MSAW, CPDLC etc are not currently available, while supported by the plugin.
In the future with the introduction of TopSky in the real system in 2022 (Portuguese estimate™, subject to change), an update to bring it up to the real life counterpart standards should follow suit.

This plugin is for users with at least a basic understanding of ATC procedures and terminology, and being familiar with the operation of the EuroScope program itself. Refer to the EuroScope documentation for the most current information on the program’s features. Because of the complexity of the plugin, some offline practice is recommended before attempting to control online traffic with it.

# Features
This plugin adds a ton of neat features and tools, some highlights are:
TSA managment, including timed and scheduled activations

![TSA Managment](https://user-images.githubusercontent.com/13833056/97945690-23e28800-1d80-11eb-8bce-a43d5735573e.jpg)

Conflict and Risk Display (CARD)

![CARD](https://user-images.githubusercontent.com/13833056/97945835-9bb0b280-1d80-11eb-9498-1f6bd4ed1c4e.png)

Vertical Aid Window (VAW)

![VAW](https://user-images.githubusercontent.com/13833056/97945927-de728a80-1d80-11eb-821a-d5ac31cc7cac.png)

Segregated Area Probe (SAP)

Airport specific Transition level


![SAP](https://user-images.githubusercontent.com/13833056/97945951-ecc0a680-1d80-11eb-9e7f-b42b4cf19085.png)

Medium Term Conflict Detection

Ground Radar based on the SAAB A3000 A-SMGCS system

![Ground Radar](https://user-images.githubusercontent.com/13833056/97946021-18dc2780-1d81-11eb-9ae6-3174cfb085c1.png)

![Ground Radar](https://user-images.githubusercontent.com/13833056/97946071-33ae9c00-1d81-11eb-9a52-7af0abfd59d6.png)

Realist radar Maps
![Radar Maps](hhttps://user-images.githubusercontent.com/13833056/97946155-6d7fa280-1d81-11eb-9e83-135feb0831ae.png)


And a lot more

# Instalation
TopSky is included in the Sector Package of Portugal vACC, and is the recommended method of installation. If for some reason you wan't to do it yourself, first, why, second, [Download](https://github.com/pinatacolada/topskylppc/archive/1.4.zip) and unzip the contents of topskylppc-1.4 folder into the folder where you keep your LPPC sector file. It should ask you to overwrite some files, say yes to those.

Then, on Euroscope, go to Other Settings/Plug-ins. Click on load and navigate to where you dropped the files before. Select the TopSky.dll and then the GRplugin.dll files.

Both plug-ins should now show in the plug-in list. Select each and on the Forbidden to draw on types list click on << on all entries, so as to allow the plug-ins to draw on the screen.

Close and reopen Euroscope, save all changes. 

When prompted during startup, use the LPPC_CTR_TS.prf profile.

Ta-daa, it should work now.

The Ground Radar is only available through the _GND_TSGR.asr. Use F1+6, F1+7, F1+8, F1+9 to use open Madeira, Faro, Lisboa and Porto respectively.

To use CPDLC you need a [Hoppie account](https://www.hoppie.nl/acars/system/register.html). Paste your Hoppie code inside the TopSkyCPDLChoppieCode.txt file and save. The code is now saved, but each time you connect to Vatsim you will still need to initate the CPDLC connection via the CPDLC menu. It's just pressing one button that says connect, really it's not hard.

# Disclamer
Although - as its name suggests - the plugin is based on the TopSky ATM system, it is in no way affiliated with or endorsed by Thales Group. Similarities between plugin features and the real system are not entirely coincidental, but anyone planning to use the plugin as a real world training aid really should know better…



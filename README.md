# TopSky plugin for Portugal vACC 
[![latest-version](https://img.shields.io/github/v/release/pinatacolada/topskylppc?include_prereleases)](https://github.com/pinatacolada/topskylppc/releases)  [![topsky-version](https://img.shields.io/badge/TopSky-2.6.1-blue.svg)](https://forum.vatsim-scandinavia.org/d/228-topsky-plugin-26-beta-1)
[![gr-version](https://img.shields.io/badge/Ground%20Radar-1.6b5-blue.svg)](https://forum.vatsim-scandinavia.org/d/256-ground-radar-plugin-16-beta-5)
[![cdm-version](https://img.shields.io/badge/CDM-2.2.8.23-blue.svg)](https://github.com/rpuig2001/CDM)
[![rwym-version](https://img.shields.io/badge/RwyMan-0.15.3-blue.svg)](https://gitlab.com/portugal-vacc/runwaymanager/-/releases/v0.15.3)

The TopSky plugin is developed and maintained by Juha Holopainen from VATSIM Scandinavia. It is an almost complete set of tag items, tag menus, graphical elements on the radar display and some additional functionality based around the real world TopSky ATM system. 

The aim of this project is to emulate as much as possible TOPLIS system in Euroscope.

This plugin is for users with at least a basic understanding of ATC procedures and terminology, and being familiar with the operation of the EuroScope program itself. Refer to the EuroScope documentation for the most current information on the program’s features. Because of the complexity of the plugin, some offline practice is recommended before attempting to control online traffic with it.

# Features
This plugin adds a ton of neat features and tools, some highlights are:

TSA management, including timed and scheduled activations;

![TSA Managment](https://user-images.githubusercontent.com/11005754/193365249-1ad20c2d-6476-46e9-ad2f-30d3d347b934.png)


Conflict and Risk Display (CARD);

![CARD](https://user-images.githubusercontent.com/11005754/193365276-64eb1d0a-79b1-4cc1-9bf4-bafb6d1696c8.png)

Vertical Aid Window (VAW);

![VAW](https://user-images.githubusercontent.com/11005754/193365325-57a22810-5f4f-4c0c-a05b-bc9270047430.png)


Segregated Area Probe (SAP);

![SAP](https://user-images.githubusercontent.com/11005754/193365380-9757378c-f99a-42ab-b349-845670150ad9.png)


Airport specific Transition level;

![TL](https://user-images.githubusercontent.com/11005754/193365416-6e6b57ea-160e-49b4-a870-b2ff73859c06.png)

Medium Term Conflict Detection;

![MTCD](https://user-images.githubusercontent.com/11005754/193365905-03177612-d221-4ff2-983b-2e8bf8b7fedb.png)


CPDLC;

![image](https://user-images.githubusercontent.com/11005754/193365748-9d55bc71-eb26-4055-909d-7ce48400e9bf.png)


Realistic radar Maps;

![image](https://user-images.githubusercontent.com/11005754/193365835-079a8e3e-56fb-40a5-b618-27116edaf92f.png)


Ground Radar based on the SAAB A3000 A-SMGCS system (Ground Radar Plugin)

![Ground Radar](https://user-images.githubusercontent.com/13833056/97946021-18dc2780-1d81-11eb-9ae6-3174cfb085c1.png)

![Ground Radar](https://user-images.githubusercontent.com/13833056/97946071-33ae9c00-1d81-11eb-9a52-7af0abfd59d6.png)




And a lot more...

# Instalation
TopSky is already included in the Sector Package of Portugal vACC.

When prompted during startup, use the LPPC ACS.prf for CTR of LPPC APS.prf for APP and below.

Ta-daa, it should work now.

The Ground Radar is only available through the _GND_TSGR.asr. Use F1+6, F1+7, F1+8, F1+9 to use open Madeira, Faro, Lisboa and Porto respectively.

To use CPDLC you need a [Hoppie account](https://www.hoppie.nl/acars/system/register.html). Paste your Hoppie code inside the TopSkyCPDLChoppieCode.txt file and save. The code is now saved, but each time you connect to Vatsim you will still need to initate the CPDLC connection via the CPDLC menu. It's just pressing one button that says connect, really it's not hard.

# Disclamer
Although - as its name suggests - the plugin is based on the TopSky ATM system, it is in no way affiliated with or endorsed by Thales Group. Similarities between plugin features and the real system are not entirely coincidental, but anyone planning to use the plugin as a real world training aid really should know better…

# License

topskylppc is available under the GPL-3.0 license. See the [LICENSE](LICENSE) file for more info.

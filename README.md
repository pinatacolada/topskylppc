# TopSky plugin for Portugal vACC

The TopSky plugin (a.k.a. The Plugin Formerly Known As “EUROCAT 2000 E”) is developed and maintained by Juha Holopainen from VATSIM Scandinavia. It is an almost complete set of tag items, tag menus, graphical elements on the radar display and some additional functionality based around the real world TopSky ATM system. 

As of right now, the aim of this plugin is to provide additional tools for ATC, while creating a system closer to LISATM than TopSky. As such, some tools and safety nets like PDC, MSAW, CPDLC etc are not currently available, while supported by the plugin.
In the future with the introduction of TopSky in the real system in 2022 (Portuguese estimate™, subject to change), an update to bring it up to the real life counterpart standards should follow suit.

This plugin is for users with at least a basic understanding of ATC procedures and terminology, and being familiar with the operation of the EuroScope program itself. Refer to the EuroScope documentation for the most current information on the program’s features. Because of the complexity of the plugin, some offline practice is recommended before attempting to control online traffic with it.

# Features
This plugin adds a ton of neat features and tools, some highlights are:
TSA managment, including timed and scheduled activations
Conflict and Risk Display (CARD)
Segregated Area Probe (SAP)
Airport specific Transition level
Medium Term Conflict Detection
Ground Radar based on the SAAB A3000 A-SMGCS system

And a lot more

# Instalation
Download and unzip the contents of topskylppc-master folder into the folder where you keep your LPPC sector file. It should ask you to overwrite some files, say yes to those.
Then, on Euroscope, go to Other Settings/Plug-ins. Click on load and navigate to where you dropped the files before. Select the TopSky.dll and then the GRplugin.dll files.
Both plug-ins should now show in the plug-in list. Select each and on the Forbidden to draw on types list click on << on all entries, so as to allow the plug-ins to draw on the screen.
Close and reopen Euroscope, save all changes.
Ta-daa, it should work now.
Note: The Ground Radar is only available through the LPPT_GND_TSGR.asr. Go to OPEN SCT/Open, navigate to LPPC\ASR and open the LPPT_GND_TSGR to use it.

# Disclamer
Although - as its name suggests - the plugin is based on the TopSky ATM system, it is in no way affiliated with or endorsed by Thales Group. Similarities between plugin features and the real system are not entirely coincidental, but anyone planning to use the plugin as a real world training aid really should know better…


---
---

1.   Download and install EuroScope from https://www.euroscope.hu/install/EuroScopeSetup32.msi. By default EuroScope will ask to install in 'C:\Program Files (x86)\EuroScope'. Remember where your installation directory is as it will be used a lot. It is strongly recommended not to change the installation directory.

2.   Download EuroScope Beta r34 from https://www.euroscope.hu/install/EuroScopeBeta32a34.zip

3.   Browse to your install directory and unzip the contents of the beta on top of your Euroscope installation. Overwrite the existing files. 

4.   In your install directory, right click the 'EuroScope.exe' file, and access 'Properties'.

![Properties](/assets/img/rclick.png)

5.   In Properties navigate to the 'Compatability' tab, and in the 'Settings' section, enable the 'Run this program as an administrator' option. Sector file updates will not be reliable without this option enabled. Press 'Ok' to close the dialogue.

![Admin](/assets/img/admin.png)

6.   Right click the 'EuroScope.exe' file, and select 'Send to->Desktop'. This will create a shortcut in your Desktop that points to the correct instance of EuroScope.

![Shortcut](/assets/img/short.png)

7.   Open EuroScope from the newly created shortcut. If prompted to open a profile file click 'Cancel'

8.   EuroScope will load with a blank screen. At the top of the window, click the 'OTHER SET' button and disable the 'Auto load last profile on startup' option.

![Autoload](/assets/img/aload.png)

9.   Click the 'OPEN SCT' menu and select the 'Download Sector Files ...' option.

![Download](/assets/img/dl.png)

10.  On the top half of the newly opened window, in the 'Available sector file providers' list locate the 'AeroNav GNG Sector File Provider' line and click on the 'A' or 'Auto' checkbox. This will instruct EuroScope to refresh this list if more than 12 hours have passed since the last time EuroScope was open.

![Download](/assets/img/sf1.png)

11.  Press the top half's 'Download' button. This will add other vACCs to the top list.

![Download](/assets/img/sf2.png)

12.  After the download is complete and the list updated, scroll down to 'AeroNav GNG LPPC Portugal vACC'. Click on the 'A' or 'Auto' checkbox and then the top half's 'Download' button. This will download a list of the available sector file packages published for LPPC.

![Download](/assets/img/sf3.png)

13.  The bottom half 'Provided Sector Files' list will update. In this list select the LPPC Install-Package, and without any other option selected on the right collumns, click the bottom half 'Download' button.

![Download](/assets/img/ptinst.png)

14.  The 'D' collumn will show a checkmark once the download is complete.

![Download](/assets/img/ptdld.png)

15.  On the 'LPPC Update-Package' line, select the 'A' option and click download.

![Download](/assets/img/dlup.png)

16.  When the 'D' collumn shows the download complete checkmark, on the 'LPPC TopSkyAreas' line, select the 'A' option and click download.

![Download](/assets/img/dlar.png)

17.  When the 'D' collum shows the download complete checkmark, all the required files have been downloaded. Your Window should look like below. It is important that the 'A' checkmark is set as below, as this controls the automatic sector file updates. EuroScope will now check for updates on 12 hour intervals. 

![Download](/assets/img/dlcomplete.png)

18.  Close the 'Sector File Providers' Window, and EuroScope. If EuroScope prompts to save changes, select 'Cancel All'.

19.  Navigate to 'C:\Program Files (x86)\EuroScope\LPPC\Plugins\topsky' and open the 'TopSkyCPDLChoppieCode.txt'. Replace the file's contents with your Hoppie code. If you do not have a code you need to request one at https://www.hoppie.nl/acars/system/register.html

![Download](/assets/img/cancel.png)

20.  Open EuroScope again. When prompted to 'Open a profile file', navigate to 'C:\Program Files (x86)\EuroScope' (or your instalation directory if different) and open the 'LPPC_CTR_TS.prf' file.

![Download](/assets/img/prof.png)

21.  EuroScope will now load into this window. You may now close EuroScope, installation is complete.

![Complete](/assets/img/complete.png)
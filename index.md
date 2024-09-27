---
---

1.   Install or update the Microsoft Visual C++ Redistributable 2015-2022 (x86) package, available at https://aka.ms/vs/17/release/vc_redist.x86.exe.

1.   Download and install EuroScope from https://euroscope.hu/install/EuroScopeSetup.3.2.9.msi.

1.   Open EuroScope **from the Windows Start menu**. If prompted to open a profile file click 'Cancel'

1.   EuroScope will load with a blank screen. At the top of the window, click the 'OTHER SET' button and disable the 'Auto load last profile on startup' option.

     ![Autoload](/assets/img/aload.png)

1.   Click the 'OPEN SCT' menu and select the 'Download Sector Files ...' option.

     ![Download](/assets/img/dl.png)

1.  On the top half of the newly opened window, in the 'Available sector file providers' list, select the 'Euroscope main sector file provider' line and click download

     ![Download](/assets/img/gng.png)

1.  In the 'Available sector file providers' list locate the 'AeroNav GNG Sector File Provider' line and click on the 'A' or 'Auto' checkbox. This will instruct EuroScope to refresh this list if more than 12 hours have passed since the last time EuroScope was open.

     ![Download](/assets/img/sf1.png)

1.  Press the top half's 'Download' button. This will add other vACCs to the top list.

     ![Download](/assets/img/sf2.png)

1.  After the download is complete and the list updated, scroll down to 'AeroNav GNG LPPC Portugal vACC'. Click on the 'A' or 'Auto' checkbox and then the top half's 'Download' button. This will download a list of the available sector file packages published for LPPC.

     ![Download](/assets/img/sf3.png)

1.  The bottom half 'Provided Sector Files' list will update. In this list select the LPPC Install-Package, and without any other option selected on the right collumns, click the bottom half 'Download' button.

     ![Download](/assets/img/ptinst.png)

1.  The 'D' collumn will show a checkmark once the download is complete.

     ![Download](/assets/img/ptdld.png)

1.  On the 'LPPC Update-Package' line, select the 'A' option and click download.

     ![Download](/assets/img/dlup.png)

1.  When the 'D' collumn shows the download complete checkmark, on the 'LPPC TopSkyAreas' line, select the 'A' option and click download.

     ![Download](/assets/img/dlar.png)

1.  When the 'D' collum shows the download complete checkmark, all the required files have been downloaded. Your Window should look like below. It is important that the 'A' checkmark is set as below, as this controls the automatic sector file updates. EuroScope will now check for updates on 12 hour intervals. 

     ![Download](/assets/img/dlcomplete.png)

1.  Close the 'Sector File Providers' Window, and EuroScope. If EuroScope prompts to save changes, select 'Cancel All'.

     ![Download](/assets/img/cancel.png)

1.  Navigate to '%appdata%\EuroScope\LPPC\Plugins\topsky' and open the 'TopSkyCPDLChoppieCode.txt'. Replace the file's contents with your Hoppie code. If you do not have a code you need to request one at https://www.hoppie.nl/acars/system/register.html

1.  Open EuroScope **from the Windows Start menu** again. When prompted to 'Open a profile file', navigate to '%appdata%\EuroScope' and open the 'LPPC APS.prf' file when controlling APP and lower positions, or the 'LPPC ACS.prf' file when controlling CTR enroute positions.

     ![Download](/assets/img/prof.png)

1.  EuroScope will now load into this window. Locate the mini VCCS window, to the right of the Clock window and above the METAR list, and right click either LINE 1 or LINE 2, to open the full VCCS window.

     ![VCCS mini window](/assets/img/vccsmini.png)

1.  On the VCCS window, click EDIT.
     
     ![alt text](/assets/img/vccs.png)

1.  Insert your VATSIM ID on the Nickname field.

     ![alt text](/assets/img/vccsvid.png)

1.  On the G2G PTT line, click SET. This will be your keybind to communicate with other controlers through VCCS, and must be different than the one you use on Audio for VATSIM to transmit on the frequency.

     ![alt text](/assets/img/vccsptt.png)

1.  On the Devices section, verify your Capture and Playback devices are correctly set, or change as required.

     ![alt text](/assets/img/vccsaudio.png)

1. Click OK, to close the VCCS setup dialogue, and close the VCCS full window. 

1.  Close EuroScope. When prompted to Save Changes, press save. 

1. Important note: You will need to repeat the VCCS steps on all .prf files you intend to use, as changes are only saved to the profile that was open at the time.

1. Installation is complete. **Make sure to always open EuroScope from the Windows Start menu**.

     ![Complete](/assets/img/complete.png)

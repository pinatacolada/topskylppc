---
---

If you are only updating your sectorfile, jump to step 3.

1.   Install or update the Microsoft Visual C++ Redistributable 2015-2022 (x86) package, available at https://aka.ms/vs/17/release/vc_redist.x86.exe.

1.   Download and install EuroScope from https://euroscope.hu/install/EuroScopeSetup.3.2.9.msi.

1.   Download the sector file from https://files.aero-nav.com/LPPC. Extract the contents of the zip file into '%appdata%\EuroScope\'.

1.  Navigate to '%appdata%\EuroScope\LPPC\Plugins\topsky' and open the 'TopSkyCPDLChoppieCode.txt'. Replace the file's contents with your Hoppie code. If you do not have a code you need to request one at https://www.hoppie.nl/acars/system/register.html

1.   Open EuroScope **from the Windows Start menu**. When prompted to 'Open a profile file', navigate to '%appdata%\EuroScope' and open the 'LPPC APS.prf' file when controlling APP and lower positions, or the 'LPPC ACS.prf' file when controlling CTR enroute positions.

     ![Download](/assets/img/prof.png)

1.  EuroScope will now load into this window. 

     ![Download](/assets/img/complete.png)

1.   At the top of the window, click the 'OTHER SET' button and disable the 'Auto load last profile on startup' option.

     ![Autoload](/assets/img/aload.png)

1.   Locate the mini VCCS window, to the right of the Clock window and above the METAR list, and right click either LINE 1 or LINE 2, to open the full VCCS window.

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

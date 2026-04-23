# Updating
If you are merely updating your setup:

1.   Download the [LPPC Update-Package](https://files.aero-nav.com/LPPC). Extract the contents of the zip file into `%appdata%\EuroScope`

1.  yeah that's it

# Full Installation

If you are installing for the first time, or your setup is FUBAR:

1.   Install or update the [Microsoft Visual C++ Redistributable 2015-2022 (x86) package](https://aka.ms/vs/17/release/vc_redist.x86.exe).

1.   Download and install [TrackAudio 1.3.3](https://github.com/pierr3/TrackAudio/releases/download/1.3.3/trackaudio-1.3.3-x64-setup.exe).

1.   Open TrackAudio and click on the settings cog.

     ![Autoload](/assets/img/ta1.png)

1.   Fill out the CID and password fields with your VATSIM credentials, select your appropriate hardware devices, and at least a PTT 1 key. The remaining settings may be set according to your preference, but it is recomended to be set as per below.

     ![Autoload](/assets/img/ta2.png)

1.   Download and install [VACS 2.2.0](https://github.com/vacs-project/vacs/releases/download/vacs-client-v2.2.0/vacs_2.2.0_x64-setup.exe).

1.   Open VACS. Open the settings menu, double check your hardware devices are correctly selected, then click `Transmit`.

     ![Autoload](/assets/img/vacs1.png)

1.   Set `MODE` to `Voice activation` , then click X to close the `Transmit Config` window.

     ![Autoload](/assets/img/vacs2.png)

1.   Click `Hotkeys`.

     ![Autoload](/assets/img/vacs3.png)

1.   We recommend to set the same key for `Accept first call` and `End active call` (must be different than TrackAudio PTT), then click X to close the `Hotkeys Config` window.

     ![Autoload](/assets/img/vacs4.png)

1.   Download and install [EuroScope 3.2.9](https://euroscope.hu/install/EuroScopeSetup.3.2.9.msi).

1.   Download the [LPPC Install-Package](https://files.aero-nav.com/LPPC). Extract the contents of the zip file into `%appdata%\EuroScope`

1.  Navigate to `%appdata%\EuroScope\LPPC\Plugins\topsky` and open the `TopSkyCPDLChoppieCode.txt`. Replace the file's contents with your Hoppie code. If you do not have a code you need to [request one](https://www.hoppie.nl/acars/system/register.html).

1.   Open EuroScope **from the Windows Start menu**. When prompted to `Open a profile file`, navigate to `%appdata%\EuroScope` and open the `LPPC APS.prf` file when controlling APP and lower positions, or the `LPPC ACS.prf` file when controlling CTR enroute positions.

     ![Download](/assets/img/prof.png)

1.  EuroScope will now load into this window. 

     ![Download](/assets/img/complete.png)

1.   At the top of the window, click the `OTHER SET` button and disable the `Auto load last profile on startup` option.

     ![Autoload](/assets/img/aload.png)

1.  Close EuroScope. When prompted to Save Changes, press save. 

1. Installation is complete. **Make sure to always open EuroScope from the Windows Start menu**.

     ![Complete](/assets/img/complete.png)
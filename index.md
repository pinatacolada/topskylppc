---
---
# Guide to Install

If you are only updating your sectorfile, jump to step 11.

1.   Install or update the [Microsoft Visual C++ Redistributable 2015-2022 (x86) package](https://aka.ms/vs/17/release/vc_redist.x86.exe).

1.   Download and install [TrackAudio 1.3.3](https://release-assets.githubusercontent.com/github-production-release-asset/783394445/8facb372-7337-4cdd-906d-92b67c38dbf0?sp=r&sv=2018-11-09&sr=b&spr=https&se=2026-04-01T03%3A04%3A54Z&rscd=attachment%3B+filename%3Dtrackaudio-1.3.3-x64-setup.exe&rsct=application%2Foctet-stream&skoid=96c2d410-5711-43a1-aedd-ab1947aa7ab0&sktid=398a6654-997b-47e9-b12b-9515b896b4de&skt=2026-04-01T02%3A04%3A10Z&ske=2026-04-01T03%3A04%3A54Z&sks=b&skv=2018-11-09&sig=hQJpjhW90kgCNvwGI04zaC2bGWAYbCLK4wm2GQXBOLs%3D&jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmVsZWFzZS1hc3NldHMuZ2l0aHVidXNlcmNvbnRlbnQuY29tIiwia2V5Ijoia2V5MSIsImV4cCI6MTc3NTAxMTU1OSwibmJmIjoxNzc1MDA5NzU5LCJwYXRoIjoicmVsZWFzZWFzc2V0cHJvZHVjdGlvbi5ibG9iLmNvcmUud2luZG93cy5uZXQifQ.XopmME6ouqLDM1Bo4W-zF6jKEXuDoSWzQX5Sm-p9h30&response-content-disposition=attachment%3B%20filename%3Dtrackaudio-1.3.3-x64-setup.exe&response-content-type=application%2Foctet-stream).

1.   Open TrackAudio and click on the settings cog.

     ![Autoload](/assets/img/ta1.png)

1.   Fill out the CID and password fields with your VATSIM credentials, select your appropriate hardware devices, and at least a PTT 1 key. The remaining settings may be set according to your preference, but it is recomended to be set as per below.

     ![Autoload](/assets/img/ta2.png)

1.   Download and install [VACS 2.1.0](https://release-assets.githubusercontent.com/github-production-release-asset/993241353/2dfa8910-92f1-4cf3-aae2-1bbf5e34acc8?sp=r&sv=2018-11-09&sr=b&spr=https&se=2026-04-01T03%3A04%3A03Z&rscd=attachment%3B+filename%3Dvacs_2.1.0_x64-setup.exe&rsct=application%2Foctet-stream&skoid=96c2d410-5711-43a1-aedd-ab1947aa7ab0&sktid=398a6654-997b-47e9-b12b-9515b896b4de&skt=2026-04-01T02%3A03%3A19Z&ske=2026-04-01T03%3A04%3A03Z&sks=b&skv=2018-11-09&sig=Ul5z8pHIqpk5HcHxj3kt8CyOh8qacLiCYt2DOKIWCqE%3D&jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmVsZWFzZS1hc3NldHMuZ2l0aHVidXNlcmNvbnRlbnQuY29tIiwia2V5Ijoia2V5MSIsImV4cCI6MTc3NTAwOTYxOCwibmJmIjoxNzc1MDA5MzE4LCJwYXRoIjoicmVsZWFzZWFzc2V0cHJvZHVjdGlvbi5ibG9iLmNvcmUud2luZG93cy5uZXQifQ.66xe-a-334Vwvg8fxwB5W4qgj83Asw_6SOWDcFqXmZU&response-content-disposition=attachment%3B%20filename%3Dvacs_2.1.0_x64-setup.exe&response-content-type=application%2Foctet-stream).

1.   Open VACS. Open the settings menu, double check your hardware devices are correctly selected, then click Transmit.

     ![Autoload](/assets/img/vacs1.png)

1.   Set as per below, then click X to close the Transmit Config window.

     ![Autoload](/assets/img/vacs2.png)

1.   Click Hotkeys.

     ![Autoload](/assets/img/vacs3.png)

1.   Set as per below, then click X to close the Hotkeys Config window.

     ![Autoload](/assets/img/vacs4.png)

1.   Download and install [EuroScope 3.2.9](https://euroscope.hu/install/EuroScopeSetup.3.2.9.msi).

1.   Download the [sector file](https://files.aero-nav.com/LPPC). Extract the contents of the zip file into
'%appdata%\EuroScope\'.

1.  Navigate to '%appdata%\EuroScope\LPPC\Plugins\topsky' and open the 'TopSkyCPDLChoppieCode.txt'. Replace the file's contents with your Hoppie code. If you do not have a code you need to [request one](https://www.hoppie.nl/acars/system/register.html).

1.   Open EuroScope **from the Windows Start menu**. When prompted to 'Open a profile file', navigate to '%appdata%\EuroScope' and open the 'LPPC APS.prf' file when controlling APP and lower positions, or the 'LPPC ACS.prf' file when controlling CTR enroute positions.

     ![Download](/assets/img/prof.png)

1.  EuroScope will now load into this window. 

     ![Download](/assets/img/complete.png)

1.   At the top of the window, click the 'OTHER SET' button and disable the 'Auto load last profile on startup' option.

     ![Autoload](/assets/img/aload.png)

1.  Close EuroScope. When prompted to Save Changes, press save. 

1. Installation is complete. **Make sure to always open EuroScope from the Windows Start menu**.

     ![Complete](/assets/img/complete.png)

# Guide to Update

1.   Download the [sector file](https://files.aero-nav.com/LPPC). Extract the contents of the zip file into
'%appdata%\EuroScope\'.

1.  yeah that's it
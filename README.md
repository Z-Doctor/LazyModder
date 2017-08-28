# LazyModder
LazyModder is a library developed with the purpose of making a modder's life easier. It handles many of the repetitive tasks asked upon the modder without limiting what the modder can do.

# How to contribute
With some input from Choonster, I have added a gradle to the git. So if you want to contribute, or just setup the library in your workspace, simply import the git as a general project. Then right click the project >> configure >> Add Gradle Nature.

This will import forge and set up the workspace.

# How to use the library
With some more input from Choonster, I will now detail how to use the library. First get the library version you want, see <a href="#howtobuild">How to Build</a>. Then either (1) place the jar in the libs folder (create in main directory if it is not there), or (2) right-click your project (or press alt-enter) to go to properties >> libraries >> add external jar, then go to the jar location and add the jar, expand the and double click source and navigate to the src jar create when you built the library.

The difference in these two method is the first will just require without the source being visible and the second will have the soruce visible.

To require the mod add this line to the Mod annotation: dependencies = "required-after:lazymodder@[x.x,)" 
Where x is the version of the library you want to use.

Note: If anyone knows of a simpler way to do this let me know!

<div id="howtobuild">

# How to build
Open a command prompt in the folder where the project is at by shift right clicking in the folder >> Open command window here (or Powershell). Then type .\gradlew build.
Alternatively you can just click on the build.bat to do the same thing.

</div>

# Note on contributing
If you do want to contribute make sure that (1) any modder that uses the class can override all methods within reason, (2) your class is extendable, (3) it ideally can be used in one line as is. Try to follow these guidelines and I will add more if the need arises.
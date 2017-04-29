# BloodBaubles
An addon for Blood Magic

# Getting Started
So you want to contribute to this mod? Great! There are a few things to consider for this task that will be outlined here. First getting started, it took me some time to cleanup this git repository, so it is absoultely pivital that any additions keep the format. Nothing outside of the src folder should be needed. If for some reason something is needed, it will be added after review. So make sure your .gitignore file is properly created.
#Workspace
You are responsible for setting up your workspace using any editor you want. I am using eclipse. You will need two mods and their dependencies: Blood Magic and Baubles. It is not my place to educate you on how to do this or the proper way to do so. I believe that you are more than capable.
#Version Numbering
The first version to be official released will be 2.0.0. The numbering system has its own meanings. The first is for huge updates, like for a complete overhaul of implementation (like how I updated this mod to 1.10, I completely changed the structure). The next digit is for lesser changes (e.g. additional items). The last digit is for bug fixes (there is no limit on the number, and it carries over).
#Code Format
I am aware that many coders follow a different syntax than I. However, this being my mod, my rules apply. 2 spaces instead of tabs, open brackets on the same line as method, class, enums, etc (e.g. class Example {, void method() {, etc.) If my format is inconsitent, it is either because I haven't applied them yet, or for aesthetic or readability purposes.
#Code Readability
The code should be readable. Plain and simple. I use Java 1.8 and Lymbda syntax where available. If there is a better way of doing something let me know. For the sake of readability, sometimes creating a local varaible is better than a long line of code.
#Code Utility
Ideally classes should be implemented in such a way that many items can be created with it. A perfect example are my BloodRing classes along with my BaubleRegistry and interface IAutoRegister. Those three objects make creating new items REALLY easy. Strive for something like that.
#Happy Coding
I know that this is a lot for a small project, but I want to make thse standards of all my new/current projects (old ones don't count). If you have any questions let me know. I'll try to help the best I can.
#FAQ
1. Will legacy version be supported?

Yes. My hope is that my current code will be so flexible and independent that upgrading or making them backwards compatible will be easy with minimal effort (redefining a few methods)

2. What are the dependencies?

Blood Magic & Baubles and their respective dependencies.

3. I'm getting and error running 'gradlew build'.

You need to run Gradle 3.0 or higher in order to compiler Java 8. Google Gradle 3.0

4. I can't code but I want to help.

Great! Add language support by localizing the names (no google translating please)
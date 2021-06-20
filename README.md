![Kaizo Minecraft](https://i.imgur.com/bYNIYia.png)
<div align="center">
  <img src="https://img.shields.io/github/languages/code-size/Pjiwm/kaizominecraft" />
  <img src="https://img.shields.io/bitbucket/issues-raw/pjiwm/kaizominecraft" />
  <img src="https://img.shields.io/github/commit-activity/m/pjiwm/kaizominecraft" />
  <img src="https://img.shields.io/github/last-commit/pjiwm/kaizominecraft" />
  <img src="https://img.shields.io/github/contributors/pjiwm/kaizominecraft" />
</div>
<hr/>

# What is kaizo?
Kaizo is a genre of highly difficult video game levels in the Super Mario series, originally created by T. Takemoto.
[reference](https://en.wikipedia.org/wiki/Kaizo)

The kaizo concept basically picks a game and makes its already existing components and game elements way harder and sometimes more unfair.
This can give an experienced player of a game a new and more challenging experience.

# The purpose of kaizo minecraft
The idea behind kaizo Minecraft is to make the game more challenging for experienced players.
Minecraft is already over a decade old and there are many skilled and experienced players.
Vanilla Minecraft can often be too easy fir them, especially due to recent updates which have changed a lot of game mechanics which sometimes makes the game less hard.
Compared to older versions of the game, you often see players getting very good gear and equipment within a couple hours of playing.
An example for instance, diamonds have become way more accessible and easier to get, by for example treasure chests or by mining with fortune. Or think of the many other enchants that make the game a lot easier.
This is where kaizo Minecraft comes into play. 
The kaizo plugin makes the difficulty on a server way higher to balance out overpowered gear and items.
This way Minecraft becomes harder again for knowledgeable and experienced players.
We hope to give these players a new, fun and sometimes more frustrating experience with the game, without having to play mudpacks or other systems that give new content to minecraft. 
With kaizo minecraft it’s possible to play the game you know, but with more of a challange.

# Features
kaizo minecraft mostly changes the bahavior of mobs.
Passive mobs such as pigs and sheeps will run away from the player at all times at a higher speed.
Some neutral mobs and all hostile mobs will attack the player with stronger abilities such as: 
speed, strength, tipped arrows, bigger explosions, weapon enchants.
The mob behavior of some mobs have been changed as well.

![creeper](https://i.imgur.com/q9TiY1W.png)

Creepers for example will have higher explosions and have a higher change of being charged.
Creepers can ignite themselves at random distances from the player and they can have random explosion powers.
Every creeper is a little different hence the player has to be more careful when approaching this mob.

![skeleton](https://i.imgur.com/lQ1wmjF.png)
Skeletons have unique tipped arrows which makes them more annoying to deal with.
The potion effect that only occurs when eating a puffer fish nausea  is used in this case as well.

# Installation
### requirements
[maven](https://maven.apache.org/download.cgi)

### Clone project
```
git clone https://github.com/Pjiwm/kaizominecraft.git
```
### Build jar file
open kaizominecraft directory, open a terminal in the folder and execute:
```
mvn install
```
### Place plugin in server
copy jar file from:
`kaizominecraft > target > kaizominecraft.jar`
go to your server folder and place jar file in `plugins`

### turn on plugin on server
start up server or reload server:
```
rl confirm
```


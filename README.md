# Fiber modded Minecraft client

![Imgur](https://i.imgur.com/QWbxwe4.gif)

### Description
Old training project. Based on Mod Coder Pack (1.12) Minecraft source isn't included but some files were edited - client update, render and general hooks. Client had no purpose, so modules were created in random order and with no priority on the given functions. The goal wasn't bypassing anticheats, so most things only work on the vanilla server. (modules that bypass some checks will be listed)

Client development is currently suspended, but who knows... 
Feel free to use code in your own project

### Functions
 - Base with easy module creation and handling
    - Module is auto added to list
    - Module toggle can be bound to key
    - Module is categorized and 
 - GUI
     - ![Imgur](https://i.imgur.com/NkUqyZr.gif)
     - Active modules list
     - Arrow menu for toggling modules
        - Auto rendering modules in menu categories
        - Menu remembers last selected option in each category
     - Animations and excessively colorful design
     - Coords display with nether equivalent 
 - Module options
    - Base allowing to add multiple options for each module
    - Controlled with commands
 - Custom commands 
    - Base allowing to add multiple commands that can control options and toggle them
    - Basic command detection and interpretation
    - ![Imgur](https://i.imgur.com/DmRzJm2.gif)
 - Config save
    - Basic function that is saving active modules when closing Minecraft and loading them on launch
    - Config is saved in .minecraft files
 - Client utils
    - Rainbow generator
    - Block utils
        - get closest block to entity/block
        - is block neighbour of block
        - is valid block 
    - Combat utils
        - get best entity
        - get correct player rotation
        - attack entity (client side)
    - Login utils
        - can log in to official Minecraft account
    - Packet utils
        - override rotations sended to server
    - Player utils
        - face entity/block (client/server side)
        - place block (client side)
    - Render utils (colors are controlled by base)
        - draw tracer (almost working)
        - draw esp of entity/block
- Modules
   - Combat
        - CrystalAura - auto Ender Crystal place&destroy (for pvp known from 2b2t server)
        - AutoArmor - equip best armor when inventory is opened
        - AutoSoup - auto soup eating when health is under some value (for servers with old soups)
        - KillAura - attack player in range (code for client and packet version)
   - Movement
        - AntiKnockback
        - Scaffold - place block under player (not done)
        - NoFall - cancel falling damage
        - Sprint - auto sprint
        - BHop - speed
        - Flight
        - Jesus - walk on water
    - Render
        - AntiHurtCam - no red tint and shake on damage
        - Fullbright
        - Freecam (placeholder)
        - Tracers - draw line from screen to each entity (not done)
        - ESP - draw box around each entity (custom color)
    - Player
        - AutoTool - select on toolbar best possible tool to action now performed 
    - Misc
        - AutoAdvert - module for triggering people lmao
        - AntiAFK
        - Nuker - destroy everything around (creative)

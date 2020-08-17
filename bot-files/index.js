const Discord = require('discord.js');
const { prefix, token } = require('./config.json');
const client = new Discord.Client();

client.once("ready", () => {
    console.log("Ready!");
    // console.log(client)
});

// Listener event
client.on('message', message => {
    if (!message.content.startsWith(prefix) || message.author.bot) return;

    const args = message.content.slice(prefix.length).split(/ +/);
    const command = args.shift().toLowerCase();

    if (message.content.startsWith(`${prefix}ping`)) {
        message.channel.send("Pong");
    }
    else if (command === 'args-info') {
        if (!args.length) {
            return message.channel.send(`You didn't provide any arguments, ${message.author}!`);
        }
        else if (args[0] === 'foo') {
            return message.channel.send('bar');
        }

        message.channel.send(`Command name: ${command}\nArguments: ${args}`);
    }
    
    // don't worry about startsWith yet
    else if (message.content.startsWith(`${prefix}help menu`)) {
        message.channel.send("Help Menu 2");
    }
    else if (message.content.startsWith(`${prefix}help`)) {
        message.channel.send('Help Menu: ' 
        + '\n1. First item'
        + '\n2. Second item'
        + '\n3. Third item');
    }
    else if (message.content === `${prefix}server`) {
        message.channel.send(`This server's name is: ${message.guild.name}`
        + `\nNumber of members in server: ${message.guild.memberCount}`); 
    }
});

// Bot token
client.login(token);

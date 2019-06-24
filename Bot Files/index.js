// https://discordapp.com/oauth2/authorize?client_id=591814024883339272&scope=bot

const Discord = require('discord.js');
const { prefix, token } = require('./config.json');
const client = new Discord.Client();

client.once("ready", () => {
    console.log("Ready!");
    // console.log(client)
});

// Listener event
client.on('message', msg => {
    if (msg.content.startsWith(`${prefix}ping`)) {
        msg.channel.send("Pong");
    }
    // don't worry about startsWith yet
    else if (msg.content.startsWith(`${prefix}help menu`)) {
        msg.channel.send("Help Menu 2");
    }
    else if (msg.content.startsWith(`${prefix}help`)) {
        msg.channel.send("Help Menu:");
    }
    else if (msg.content === `${prefix}server`) {
        msg.channel.send(`This server's name is: ${msg.guild.name}`
        + `\nNumber of members in server: ${msg.guild.memberCount}`); 
    }
});

// Bot token
client.login(token);
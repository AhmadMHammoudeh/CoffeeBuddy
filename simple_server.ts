//Please see these docs: https://open-wa.github.io/wa-automate-nodejs/classes/client.html#middleware

import { create, Client, Message } from '@open-wa/wa-automate';
import * as request from 'request';
// import { userInfo } from 'os';
// import fetch from 'node-fetch';
// import { create, Client } from '../src/index';
// import {ev} from '@open-wa/wa-automate';
const axios = require('axios').default;

const { default: PQueue } = require("p-queue");
const queue = new PQueue({ concurrency: 5 });

const express = require('express');
const app = express();
app.use(express.json({limit: '2000mb'})) //add the limit option so we can send base64 data through the api
// app.use(express.json());
const PORT = 8082;

//Create your webhook here: https://webhook.site/
const WEBHOOK_ADDRESS = 'https://webhook.site/c4aa5304-6a04-458f-8b0d-7d55e94f8713'

// async function fire(data){
//     return await axios.post(WEBHOOK_ADDRESS, data)
// }

// const wh = event => async (data) => {
//     const ts = Date.now();
//     return await queue.add(()=>fire({
//         ts,
//         event,
//         data
//     }))
// }

type GetUsersResponse = {
  username: string;
};

let username
let state = 0
async function start(client:Client){
  app.use(client.middleware);
  client.onMessage(async message=> {
    if(message.body.toLowerCase() == 'help') {
      await client.sendText(message.from,'Hey, welcome to 42 Abu Dhabi\'s Daily Coffee Break.\n 1- Follow the steps to register\n 2- Send \'encounter\' to get your peer for the day.');
      state++
    }
    if (message.body.toLowerCase() == 'encounter') {
      const response = await fetch("http://localhost:8080/CoffeeBreak/Generate/"+message.from, {
        method: 'GET',
        headers:{
          Accept:'application/json',
        },
      });
      console.log("It works")
      const result = (await response.json()) as GetUsersResponse
      await client.sendText(message.from,'Your encounter for today is ' + result.username);
      state = 0
    }
    if(message.body.toLowerCase() == 'hi' && state == 0) {
      await client.sendText(message.from,'Hey, welcome to 42 Abu Dhabi\'s Daily Coffee Break.\n Would you like to register?');
      state++
    }
    else if(message.body.toLowerCase() == 'yes' && state == 1) {
      await client.sendText(message.from,'To begin your onboarding we need some information from you, could you provide us with your intra username?');
      state++
    }
    else if(state == 2) {
      username = message.body
      await client.sendText(message.from,'Is you the username you inserted correct?')
      state++
    }
    else if(message.body.toLowerCase() == 'yes' && state == 3) {
    await client.sendText(message.from, 'Great, one last thing we need from you 🙊! Would you prefer a social encounter or an educational one today?!\n Send \'1\' for social or \'2\' for educational.\n Or if you would like to skip today send \'0\'.')
      state++
    }
    else if((message.body.toLowerCase() == '1' || message.body.toLowerCase() == '2')  && state == 4) {
      let social
      if (message.body == '1')
        social = true
      if (message.body == '2')
        social = false
      fetch("http://localhost:8080/CoffeeBreak/DailyCoffee", {method: 'POST',
      headers: {
        'content-type': 'application/json;charset=UTF-8',
      },
      body: JSON.stringify({
        login: username,
        number: message.from,
        purpose: social,
        daily: true
      }),
    })
    await client.sendText(message.from, 'Great, welcome on board our coffee club!!')
      state++
      state = 0
    }
    else if(message.body.toLowerCase() == '0' && state == 4){

    }
    else if(message.body.toLowerCase() == 'no' && state == 3) {
      await client.sendText(message.from, 'try again!!')
      state--
    }
  })
  client.sendText("971506952822@c.us", "Hi")
  app.listen(PORT, function () {
    console.log(`\n• Listening on port ${PORT}!`);
  });
}

create({
    sessionId:'session1'
})
  .then(async client => await start(client))
  .catch(e=>{
    console.log('Error',e.message);
  });
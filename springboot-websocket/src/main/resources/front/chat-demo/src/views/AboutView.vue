<template>
  <v-app>
    <v-container fluid>
      <v-row style="width: 100%">
        <!-- Friends List -->
        <v-col cols="3" class="friend-list">
          <v-navigation-drawer v-model="drawer" :rail="rail" permanent @click="rail = false">
            <v-list-item

                prepend-avatar="https://c-ssl.duitang.com/uploads/blog/202305/14/DWSoXQWaTd5pvnp.png"
                title="你好明日香"
                nav
            >
              <template v-slot:append>
                <v-btn icon variant="text" @click.stop="click">
                  <v-icon>mdi-chevron-left</v-icon>
                </v-btn>
              </template>
            </v-list-item>

            <v-divider></v-divider>

            <v-list density="compact" nav>
              <v-list-item
                  v-for="(friend, index) in friends"
                  :key="index"
                  @click="selectFriend(friend)"
                  :class="{'selected-friend': selectedFriend.name === friend.name}"
              >
                <v-list-item-avatar>
                  <v-avatar color="blue">
                    <v-img style="width:100%" alt="Avatar" :src="friend.avatar"></v-img>
                    <span>{{ friend.name.charAt(0) }}</span>
                  </v-avatar>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title>{{ friend.name }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-navigation-drawer>
        </v-col>

        <!-- Chat Area -->
        <v-col cols="9">
          <div class="chat-area">
            <v-toolbar flat style="width: 100%">
              <v-toolbar-title>当前与 {{ selectedFriend.name }} 聊天</v-toolbar-title>
              <v-spacer></v-spacer>
              <v-badge :content="unreadMessages" color="red">
                <v-icon>mdi-bell</v-icon>
              </v-badge>
            </v-toolbar>

            <v-card class="chat-card">
              <v-card-text class="chat-messages">
                <div v-for="(message, index) in messages" :key="index">
                  <div class="message" :class="{'sent': message.sender === '真嗣', 'received': message.sender !== '真嗣'}">
                    <span>{{ message.sender }}: {{ message.content }}</span>
                  </div>
                </div>
              </v-card-text>

              <v-card-actions class="chat-actions">
                <v-text-field
                    v-model="newMessage"
                    label="Type a message"
                    @keyup.enter="sendMessage"
                    outlined
                    dense
                    class="message-input"
                ></v-text-field>
                <v-btn @click="sendMessage" color="primary">发送</v-btn>
              </v-card-actions>
            </v-card>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script setup>
import { reactive, ref, onMounted } from "vue";

// WebSocket URL, replace with your server URL
const WS_URL = "ws://localhost:8080/chat/"+'明日香';

// Reactive states
const drawer = ref(true);
let rail = ref(true);
const friends = reactive([
  { name: '真嗣', avatar: 'https://img.3dmgame.com/uploads/images/news/20210405/1617609325_143131.jpg' },

]);
const selectedFriend = ref({ name: '真嗣' });
const messages = ref([
  { sender: '真嗣', content: 'Hi there!', },
  { sender: '明日香', content: 'Hello!' }
]);
const newMessage = ref('');
const unreadMessages = ref(2);
let websocket = null;

// Methods
const click = () => {
  rail.value = !rail.value;
};

const selectFriend = (friend) => {
  selectedFriend.value = friend;
  messages.value = []; // Load messages for the selected friend
};

const sendMessage = () => {
  if (newMessage.value.trim()) {
    const message = {   isSystem:false , sender: '明日香', content: newMessage.value, toReceiver: selectedFriend.value.name };
    messages.value.push(message);
    websocket.send(JSON.stringify(message));
    // websocket.send(newMessage.value);
    newMessage.value = '';
  }
};
/**
 * 接收系统消息
 * @param event
 */
const onMessage = (event) => {
  const message = JSON.parse(event.data);

  if (message.isSystem){
    message.content='System:'+message.content
  }
  messages.value.push(message);
  unreadMessages.value++;

  // alert("接收到消息"+event.data)
};

// Lifecycle hooks
onMounted(() => {
  websocket = new WebSocket(WS_URL);

  websocket.onmessage = onMessage;
});
</script>

<style>
.friend-list {
  color: #fff;
  height: 100vh;
  overflow-y: auto;
}

.chat-area {
  width: 100%;
  margin-left: 100px;
  align-items: center;
  justify-content: center;
}

.selected-friend {
  background-color: #2a2a2a !important;
}

.chat-card {
  height: 90vh;
  display: flex;
  flex-direction: column;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  height: 600px;
  background-color: #f5f5f5;
}

.message {
  margin: 100px 0;
  padding: 10px;
  border-radius: 5px;
}

.message.sent {
  background-color: #3f51b5;
  color: #fff;
  align-self: flex-end;
}

.message.received {
  background-color: #e0e0e0;
  color: #000;
  align-self: flex-start;
}

.chat-actions {
  display: flex;
  align-items: center;
  background-color: #fff;
  padding: 10px;
}

.message-input {
  flex: 1;
  margin-right: 10px;
}
</style>

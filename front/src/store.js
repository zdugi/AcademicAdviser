import { createStore } from 'vuex'
import axios from 'axios';


export const store = createStore({
  state() {
    return {
      token: localStorage.getItem('user-token') || '',
      careerQuestions: [],
      bigFiveQuestions: []
    }
  },
  mutations: {
    setCareerQuestions(state, questions) {
      state.careerQuestions = questions;
    },
    setBigFiveQuestions(state, questions) {
      state.bigFiveQuestions = [];
      for (let question of questions) {
        state.bigFiveQuestions.push({
          id: question.id,
          text: question.text,
          picked: Math.floor(Math.random() * 2 + 3),
        });
      }
    },
    login(state, token) {
      state.token = token
    },
    logout(state) {
      state.token = ''
    }
  },
  actions: {
    // eslint-disable-next-line no-unused-vars
    requestRegistration: ({ commit }, user) => {
      return new Promise((resolve, reject) => {
        axios
          .post(`http://localhost:8080/api/auth/registration`, user)
          .then(response => {
            resolve(response)
          })
          .catch(err => {
            reject(err)
          })
      })
    },
    requestAuth: ({ commit }, user) => {
      return new Promise((resolve, reject) => {
        axios
          .post(`http://localhost:8080/api/auth/login`, user)
          .then(response => {
            var token = response.data.accessToken
            localStorage.setItem('user-token', token)
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
            commit('login', token)
            resolve(response.data)
          })
          .catch(err => {
            localStorage.removeItem('user-token')
            reject(err)
          })
      })
    },
    requestLogout: ({ commit }) => {
      return new Promise((resolve) => {
        commit('logout')
        localStorage.removeItem('user-token')
        // remove the axios default header
        delete axios.defaults.headers.common['Authorization']
        resolve()
      })
    },
    submitBigFiveSurvey({ commit }, survey) {
      return new Promise((resolve, reject) => {
        axios.post('http://localhost:8080/api/big-five/survey', survey).then(
          (response) => {
            commit('setCareerQuestions', response.data);
            resolve(response.data);
          }
        ).catch(() => {
          reject();
        })
      })
    },
    submitCareerTest({ commit, state }, careerTest) {
      console.log(commit, state, careerTest)
      return new Promise((resolve, reject) => {
        axios.post('http://localhost:8080/api/career-test', careerTest).then(
          (response) => {
            console.log(response);
            resolve(response);
          }
        ).catch(() => {
          reject()
        })
      });
    },
    getBigFiveTest({ commit }) {
      return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/api/big-five/survey").then(
          (response) => {
            commit('setBigFiveQuestions', response.data.questionDTOList);
            resolve(response);
          }
        ).catch(() => {
          reject()
        })
      });
    }
  },
  getters: {
    isAuthenticated: state => !!state.token
  }
})
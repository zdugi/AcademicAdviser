<template>
  <div class="survey">
    <h3>Career Test</h3>
    <form @submit="submit">
      <ul>
        <v-container v-for="(pair, index) in careerTest" :key="pair.id">
          <li v-bind:class="{ gray: index % 2 == 1 }">
            <span>{{ pair.questionA }}</span>
            <div>
              <input
                type="number"
                max="2"
                min="0"
                v-bind:name="pair.id"
                v-model="pair.questionAScore"
                @change="pairHandler(index, 0)"
              />
            </div>
          </li>
          <li v-bind:class="{ gray: index % 2 == 1 }">
            <span>{{ pair.questionB }}</span>
            <div>
              <input
                type="number"
                max="2"
                min="0"
                v-bind:name="pair.id"
                v-model="pair.questionBScore"
                @change="pairHandler(index, 1)"
              />
            </div>
          </li>
        </v-container>
      </ul>
      <button class="float-right">Pošalji</button>
    </form>
  </div>
</template>
<script>
export default {
  name: "CareerTest",
  data: () => {
    return {
      careerTest: [],
    };
  },
  methods: {
    submit(event) {
      event.preventDefault();

      let payload = {
        answers: [],
      };

      // check
      for (let q of this.careerTest) {
        if (q.questionAScore == null || q.questionBScore == null) {
          alert("Fill all!");
          return;
        }

        payload["answers"].push({
          id: parseInt(q.id),
          questionAScore: parseInt(q.questionAScore),
          questionBScore: parseInt(q.questionBScore),
        });
      }

      this.$store.dispatch("submitCareerTest", payload).then(() => {
        // redirect
        console.log("poslato");
      });
    },
    pairHandler(index, questionNum) {
      let pair = this.careerTest[index];
      if (
        (pair.questionAScore == 2 && pair.questionBScore == 2) ||
        (pair.questionAScore == 1 && pair.questionBScore == 1) ||
        (pair.questionAScore == 0 && pair.questionBScore == 0)
      ) {
        if (questionNum == 0) {
          pair.questionBScore = null;
        } else {
          pair.questionAScore = null;
        }
      }
    },
  },
  mounted() {
    for (let pair of this.$store.state.careerQuestions.questions) {
      let a = Math.floor(Math.random() * 3);
      let b = a;
      do {
        b = Math.floor(Math.random() * 3);
      } while (a === b);
      this.careerTest.push({
        id: pair.id,
        questionA: pair.questionA,
        questionB: pair.questionB,
        questionAScore: a,
        questionBScore: b,
      });
    }
  },
};
</script>

<style>
.survey {
  width: 800px;
  margin: 50px auto;
  padding-bottom: 100px;
}

ul {
  list-style: none;
}

li {
  margin: 0;
  padding: 5px;
}

li div,
li span {
  display: inline;
}

.gray {
  background-color: #e6e4e4;
}

input {
  width: 70px !important;
  margin-left: 50px;
  background-color: #fff !important;
}
</style>
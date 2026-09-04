# Moxy Football Quiz — Backend

A REST API for a football quiz app with three game modes: trivia, career path guessing, and grid based achievement matching. Built in Java with Spring Boot.

This is the backend half of a two repo project. The frontend (React, TypeScript) lives in a separate repo here: https://github.com/devMoxy/Football-Quiz-Frontend. On its own this repo is just an API, there's no UI in here.

Live API: https://football-quiz-lusl.onrender.com
Live frontend that talks to it: https://football-quiz-frontend-ashen.vercel.app

## Stack

Java 21, Spring Boot, Spring Data JPA, Maven, Postgres in production (hosted on Neon), H2 for local development, Docker for the actual deployed build.

## What's In Here

Gameplay endpoints are public with no authentication, since real players calling them anonymously is the whole point. There's no user accounts or login in this project, it doesn't need one for gameplay.

Content management (creating questions, players, achievements, etc.) is separated behind a key check, since this is a live public API and write access shouldn't be open to anyone who finds the URL.

## The Genuinely Hard Part

The most instructive bug in this project wasn't really a code bug, it was a startup script with no memory. There's a seeder class that used to insert some baseline data every time the app started, with no check for whether that data already existed. Every restart on the hosting platform (which happens on every deploy, and also whenever the free tier wakes up from sleep) quietly inserted another full copy of the same rows. I found this by pulling real row counts from the database and noticing every single table's count divided evenly by 27, meaning the app had restarted 27 times and duplicated its baseline data 27 times over, silently, with nobody noticing because nothing ever threw an error. The actual fix was small, an idempotency check before each insert, but finding it meant not trusting that "the code runs without errors" is the same thing as "the code is correct."

A smaller but similarly sneaky one: the achievement matching mode builds two separate lists, one of achievements and one of players, and shuffles each of them independently before sending them to the frontend. That sounds safe, but both lists were originally built by looping over achievements in order and appending each one's matching player as it went, so shuffling each list on its own still left position 0 in both lists referring to the same pair, position 1 to the same pair, and so on. The lists were random, their relationship to each other wasn't. Fixed with one extra shuffle applied after the lists were built instead of before.

## Running It Locally

Needs Java 21.

```bash
./mvnw spring-boot:run
```

With no extra setup this runs against an in memory H2 database using default Spring profiles. Nothing persists between restarts, which is fine for trying things out locally but never use this against real content, it disappears the moment you stop the process.

To run against a real Postgres database instead, set your own database connection details as environment variables and run with the prod profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

The frontend expects this API on port 8080 locally by default.

## Deploying

Runs as a Docker container:

```bash
docker build -t football-quiz .
```

The actual deployment (Render) builds from the same Dockerfile on every push to main.

## Architecture

<img width="650" height="600" alt="backend-architecture" src="https://github.com/user-attachments/assets/c4a28c7b-1972-456a-affb-12aff01a4b83" />

This diagram covers the backend side of the same two repo project. Requests come in over HTTPS, get handled by the Spring Boot controllers, and either read or write to the Postgres database on Neon. Gameplay endpoints are open to anyone, content endpoints check for the API key described above before touching the database. The whole thing runs inside a Docker container, built and deployed automatically by Render on every push to main. The frontend that actually calls this API lives in the separate repo linked at the top of this README.

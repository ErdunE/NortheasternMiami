## CS5800 Final Project - Team 3Cs Project

### Project Overview
This is the team project of CS5800 Algorithms. In this project, we aim to implement algorithms (TBD) with tested program and GUI. The project will be divided into multiple modules, with each team member responsible for specific tasks.
### Team Name: 3Cs
* **Chinese** — all of us are from China.  
* **Collaboration** — We value teamwork to achieve our goals.  
* **Creativity** — Innovation and problem is the core of how we approach challenges.  
### Team Members
* **Erdun E**
* **Hailee Wang**
* **Fengkai Liu**
### Basic Information
* **Author:** Erdun E, Hailee Wang, Fengkai Liu
* **Course:** CS5800 Algorithm
* **Program:** CS5800 Final Project
* **Professor:** Dr. Alan Jamieson
### Branching Strategy
* Main Branch: team-project  
* Feature Branch Naming Convention: workspace-[workspace-name] (e.g., workspace-Erdun)  
* All development should be done on workspace branches, and changes will only be merged into the team-project branch after review.  
### Commit and Pull Request Guidelines
* Create a workspace branch for each member specific task.  
* Push your changes to the workspace branch and open a Pull Request (PR) to merge into the team-project branch.  
* Code Review: At least one team member must review and approve the PR before merging.  
* Ensure tests pass before merging into the main project branch.  
### Collaboration Workflow
1. Clone the Repository:
```
git clone https://github.com/ErdunE/NortheasternMiami.git
cd NortheasternMiami/CS5800Algorithm/CS5800 Final Project/
```
example
```aiignore
erdune@ErdundeMacBook-Pro CS5800 Final Project % pwd
/Users/erdune/Desktop/NortheasternMiami/CS5800Algorithm/CS5800 Final Project
```
2. Create a Feature Branch:
```
git checkout -b workspace-erdun team-project
```
example
```
erdune@ErdundeMacBook-Pro CS5800 FInal Project % git checkout -b workspace-erdun team-project
M       .DS_Store
M       CS5010ProgrammingDesignParadigm/.DS_Store
M       CS5800Algorithm/.DS_Store
Switched to a new branch 'workspace-erdun'
erdune@ErdundeMacBook-Pro CS5800 FInal Project % git branch
  feature-improvement
  main
  team-project
* workspace-erdun
erdune@ErdundeMacBook-Pro CS5800 FInal Project % 

```
3. Commit and Push Your Changes:
```
git add .
git commit -m "Implemented Algorithms Logic"
git push origin workspace-Erdun
```
4. Create a Pull Request (PR):  
   * Open a PR on GitHub to merge your feature branch into the team-project branch.  
   * Request at least one other team member to review your PR.
5. Merge Changes:  
   * Once the review is approved, and all tests pass, merge the PR.
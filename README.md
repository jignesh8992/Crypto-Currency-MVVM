# Crypto-Currency-MVVM(Model-View-ViewModel)-Clean Architecture
 <img src="https://badges.frapsoft.com/os/v1/open-source.svg?v=103">
 
 
Model — View — ViewModel (MVVM) with Clean Architecture is the industry-recognized software architecture pattern that overcomes all drawbacks of MVP and MVC design patterns. MVVM suggests separating the data presentation logic(Views or UI) from the core business logic part of the application
	
#### <em>“The goal of software architecture is to minimize the human resources required to build and maintain the required system”</em>
  
  
  
#### MVVM Clean Architecture Best Pratice:**
- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes to it.
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.


#### The app has following packages:
1. ``data``: It contains all the data accessing and manipulating components.
2. ``domain``: It contain all the use-cases and bussiness logic.
3. ``presentation``: View classes along with their corresponding ViewModel.
4. ``di``: Dependency providing classes using Dagger-hilt.
5. ``common``: Utility classes.


## Screenshot
<img src="https://github.com/jignesh8992/Crypto-Currency-MVVM/blob/master/screenshots/1.png" width="250"/>     <img src="https://github.com/jignesh8992/Crypto-Currency-MVVM/blob/master/screenshots/2.png" width="250"/>


## Contributing
Feel free to contribute code to this project. You can do it by forking the repository via Github and sending pull request with changes.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible. Also be sure that all tests are passing.
 
## Developed By
[Jignesh N Patel](https://github.com/jignesh8992) - [jignesh8992@gmail.com](https://mail.google.com/mail/u/0/?view=cm&fs=1&to=jignesh8992@gmail.com&su=https://github.com/jignesh8992/Battery-Information&body=&bcc=jignesh8992@gmail.com&tf=1)

  <a href="https://github.com/jignesh8992" rel="nofollow">
  <img alt="Follow me on Google+" 
       height="50" width="50" 
       src="https://github.com/jignesh8992/Battery-Information/blob/master/social/github.png" 
       style="max-width:100%;">
  </a>
  
  <a href="https://www.linkedin.com/in/jignesh8992/" rel="nofollow">
  <img alt="Follow me on LinkedIn" 
       height="50" width="50" 
       src="https://github.com/jignesh8992/Battery-Information/blob/master/social/linkedin.png" 
       style="max-width:100%;">
  </a>
  
  <a href="https://twitter.com/jignesh8992" rel="nofollow">
  <img alt="Follow me on Facebook" 
       height="50" width="50"
       src="https://github.com/jignesh8992/Battery-Information/blob/master/social/twitter.png" 
       style="max-width:100%;">
  </a>
  
  <a href="https://www.facebook.com/jignesh8992" rel="nofollow">
  <img alt="Follow me on Facebook" 
       height="50" width="50" 
       src="https://github.com/jignesh8992/Battery-Information/blob/master/social/facebook.png" 
       style="max-width:100%;">
  </a>
  
## License


Copyright [2022] [Jignesh N Patel](https://github.com/jignesh8992)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

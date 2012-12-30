This file contains important architecture decisions.

1. Separation between business logic and user interface
	
	1.1 Problem:
	Main goal of this project is to code some JavaFX. Maybe port the application to another UI
	framework later. How to structure the application to be able to switch the frontend in the 
	future?

	1.2 Limiting factors:
	Business logic in Java. Shall be reused for every new UI. So: only port to frontends for 
	systems that are capable of running Java. 

	1.3 Assumptions.
		1. All UI frameworks are easy to decouple from the business logic.
		2. All frameworks have different APIs. Hence: intermediary part of the application 
		   that bridges the gap between business logic and API of the UI framework.

	1.4 Alternatives:
	Not to separate business logic from the UI. Rejected.

	1.5 Decision:
	Implementation of some kind of adapter that separates the UI from the business logic
## Macroeconomic Simulator
### By Michael Calvey

This project is designed to create a semi-realistic simulation of a full countries economy. The simulation maps the progress of a country over time, overseeing the arrival and departure of people, resources and companies.

The eventual goal of this project is the provide the user a means of manipulating every aspect of the country, from the flow of citizens, resources, companies, governments, money, debt, poverty and more.

## Components:
The simulation consists of the continuous updating of independent agents within a country.

 - Economy

The economy simulates the interaction of every company and citizen every tick. Using calculated figures for consumer confidence, the economy follows a business cycle of varying length and severity. The economy on average follows approximately 6.4% growth, which varies from that point depending on the level and type of fiscal/ monetary intervention, as set up in the Goverment class.

 - Companies

Each company is individually simulated, with employees, assets, capital, and calculated expenses and revenues every tick. Using these figures ebitda and profit are calculated and saved for monitoring purposes.

 - Citizens

At the moment the economy is set to freeze arrival/ departure of citizens, but this is a future concept that will be modelled based on current economic performance. The economy at the moment is at 100% employment with every citizen being a part of the labor force.

 - Government

The government sets and controls fiscal policy within the economy. The main methods of fiscal control are implemented in the form of modifiable taxes and spending plans.

- Business cycle

The business cycle currently follows a simple randomized-cyclical approach. Each cycle has a varying chance of experiencing either a boom or a crash at any time. At the moment, the cycle does not follow the trough-peak-trough model seen in real economies, but rather simulates the random variance in confidence and demand which have the most significant impact on economic growth every quarter. This simulates the randomness of real economies, and is a way to account for some of the complexity that is missing for simulating things like local/ national events that can influence growth.

- Graph

The project now includes a simple home-spun graphing GDP viewer that draws the current GDP every quarter.


## Planned features:
This model will likely never be 'completed', since there is always more complexity to simulate on an economic level. Here is a list of features that might be implemented in the future, in no particular order:

- Monetary policy

Monetary policy functionality and the general implementation of a floating currency are planned for future, with aspects like money supply, inflation and securities exchanging to be simulated.

- Citizen change
Unemployment, employment demand, employment flux and much more will be simulated in future

- Spending plans
Government spending plans to invest in companies and citizens livelihoods

# Flip-Display
A flip display simulator.

The idea comes from a video on YouTube:
[![Split Flap Display by Oat Foundry | Old School Departures Boards](https://res.cloudinary.com/marcomontalbano/image/upload/v1673423472/video_to_markdown/images/youtube--F8wx-h_sfR0-c05b58ac6eb4c4700831b2b3070cd403.jpg)](https://youtu.be/F8wx-h_sfR0 "Split Flap Display by Oat Foundry | Old School Departures Boards")


I thought it is very cool. Then I implemented a simulator on Android. 

## How to Add to your Project:
[![](https://jitpack.io/v/UnicornJin/Flip-Display.svg)](https://jitpack.io/#UnicornJin/Flip-Display)

Step 1. 

Add the JitPack repository to your build file

Add it in your root settings.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. 

In your app level build.gradle, add the dependency:

	dependencies {
	        implementation 'com.github.UnicornJin:Flip-Display:0.0.2'
	}

## How does it look like:
The widget simulates the old-school flip board. 
![](./materials/demo.gif)

## How to use
You can check out the "flipdisplayreferenceapp" for the example of using this widget.

There are a lot of comments in the code, you can refer to them.

I suggest using the views as a matrix. Put them all into a list for easy management.

The module provides a container to hold the messages:
        
    DisplayContent: is a container to hold messages
        DisplayContent(width: int, height: int):
            construct with given width and height
        setRow(rowNumber: int, message: string): 
            It provides methods to set message on one row
        getCharAt(row: int, column: int):
            Get the char at one position

You can define several messages you want to display on the matrix with the DisplayContent, and update your FlipCharViews. Go to reference app folder's MainActivity for details.
(Remember to consider concurrent programming)

You can customized these variables:
    


## Development Plan:
    - ViewGroup: For ease of management, just declare a view matrix and put in messages, no need to update view by view by yourself.
    - more customizable settings
    - more animation based on user feedback (Put your suggestions in Issues)

## Author's Personal Blog:

blog.unicornjin.com 
Welcome to check it out!
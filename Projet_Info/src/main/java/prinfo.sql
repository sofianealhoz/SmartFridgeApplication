-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prinfo
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `fridges`;

CREATE TABLE fridges (
  fridge_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT, -- Associates a fridge with a user using user_id
  name VARCHAR(255), -- Name of the fridge
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `fridgeingredients`;

CREATE TABLE fridgeingredients (
  fridge_ingredient_id INT AUTO_INCREMENT PRIMARY KEY,
  fridge_id INT, -- References the fridge to which the ingredient belongs
  ingredient_id INT, -- References the ingredient
  quantity DOUBLE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `ingredient_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `quantity` int DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `fridge_id` int,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (18,'meat',10,'2024-12-12','Meat','grams',0);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientsofrecipe`
--

DROP TABLE IF EXISTS `ingredientsofrecipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientsofrecipe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `r_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=597 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientsofrecipe`
--

LOCK TABLES `ingredientsofrecipe` WRITE;
/*!40000 ALTER TABLE `ingredientsofrecipe` DISABLE KEYS */;
INSERT INTO `ingredientsofrecipe` VALUES (534,'avocado',0,'2024-01-20',715521),(535,'cilantro',0,'2024-01-20',715521),(536,'roma tomato',0,'2024-01-20',715521),(537,'salad leaves',0,'2024-01-20',715521),(538,'center cut bacon',0,'2024-01-20',715521),(539,'turkey breast',0,'2024-01-20',715521),(540,'pita pockets',0,'2024-01-20',1095992),(541,'bacon',0,'2024-01-20',1095992),(542,'spring mix greens',0,'2024-01-20',1095992),(543,'tomatoes',0,'2024-01-20',1095992),(544,'roast turkey',0,'2024-01-20',1095992),(545,'ranch dressing',0,'2024-01-20',1095992),(546,'black beans',0,'2024-01-20',715391),(547,'tomatoes',0,'2024-01-20',715391),(548,'canned tomatoes',0,'2024-01-20',715391),(549,'chili beans',0,'2024-01-20',715391),(550,'kernal corn',0,'2024-01-20',715391),(551,'onion',0,'2024-01-20',715391),(552,'chicken breasts',0,'2024-01-20',715391),(553,'chicken breast cutlets',0,'2024-01-20',638288),(554,'coarse salt and ground pepper',0,'2024-01-20',638288),(555,'baby arugula',0,'2024-01-20',638288),(556,'feta cheese',0,'2024-01-20',638288),(557,'olive oil',0,'2024-01-20',638288),(558,'balsamic vinegar',0,'2024-01-20',730914),(559,'chicken breast',0,'2024-01-20',730914),(560,'m zarella cheese',0,'2024-01-20',730914),(561,'basil olive oil',0,'2024-01-20',730914),(562,'tomato',0,'2024-01-20',730914),(563,'narrow bread like a baguette',0,'2024-01-20',650646),(564,'a tomato',0,'2024-01-20',650646),(565,'sugar',0,'2024-01-20',650646),(566,'salt',0,'2024-01-20',650646),(567,'olive oil',0,'2024-01-20',650646),(568,'oregano leaves',0,'2024-01-20',650646),(569,'sobrassada',0,'2024-01-20',650646),(570,'bell pepper',0,'2024-01-20',635342),(571,'bread',0,'2024-01-20',635342),(572,'lettuce',0,'2024-01-20',635342),(573,'mayonnaise',0,'2024-01-20',635342),(574,'thick- bacon',0,'2024-01-20',635342),(575,'tomato',0,'2024-01-20',635342),(576,'butter',0,'2024-01-20',655601),(577,'canned tomatoes',0,'2024-01-20',655601),(578,'sausages',0,'2024-01-20',655601),(579,'olive oil',0,'2024-01-20',655601),(580,'penne pasta',0,'2024-01-20',655601),(581,'potatoes',0,'2024-01-20',655601),(582,'salt',0,'2024-01-20',655601),(583,'goat log',0,'2024-01-20',655649),(584,'tellicherry peppercorns',0,'2024-01-20',655649),(585,'ground beef',0,'2024-01-20',655649),(586,'ea. brioche rolls',0,'2024-01-20',655649),(587,'ea. plum tomatoes',0,'2024-01-20',655649),(588,'salt and pepper to season',0,'2024-01-20',655649),(589,'bu. watercress',0,'2024-01-20',655649),(590,'extra virgin olive oil',0,'2024-01-20',655649),(591,'chicken breasts',0,'2024-01-20',710765),(592,'onion',0,'2024-01-20',710765),(593,'corn',0,'2024-01-20',710765),(594,'black beans',0,'2024-01-20',710765),(595,'canned tomatoes',0,'2024-01-20',710765),(596,'taco seasoning',0,'2024-01-20',710765);
/*!40000 ALTER TABLE `ingredientsofrecipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `recipe_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `imageURL` varchar(255) DEFAULT NULL,
  `allergens_list` varchar(255) DEFAULT NULL,
  `instructions_list` text,
  PRIMARY KEY (`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1747694 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (635342,'BLT Sandwich','https://spoonacular.com/recipeImages/635342-556x370.jpg','','<ol><li>Brown bacon in a skillet</li><li>Remove and pat off excess oil</li><li>Slice tomato into 1/4 inches slices</li><li>Toast bread</li><li>Spread a thin layer of mayonnaise on bread</li><li>Layer all ingredients on bread and close sandwich</li><li>Add fresh cracked black pepper</li></ol>'),(638288,'Chicken Roll-Ups With Feta Cheese and Arugula','https://spoonacular.com/recipeImages/638288-556x370.jpg','','Preheat oven to 425 degrees. Season chicken with salt and pepper. On a clean work surface, lay cutlets flat (smooth sides down). Fill and roll: Layer each cutlet with arugula; crumble goat cheese in the center. Starting with the narrow end, roll up chicken tightly; seal with a toothpick.\nIn a large nonstick ovenproof skillet, heat oil over medium-high heat; swirl to coat bottom of pan. Cook, seam side down, until golden brown, 1 to 2 minutes. Turn chicken.\nTransfer skillet to oven. Cook until chicken is opaque throughout, 10 to 12 minutes. Remove toothpicks, and slice chicken crosswise before serving, if desired.'),(650646,'Majorcan Toasts','https://spoonacular.com/recipeImages/650646-556x370.jpg','','Put broiler on and toast the breads on one side for about 2 minutes (especially if your top rack is right up against the broiler) or until they are golden brown.\nIn the meantime, combine in a bowl the tomatoes, salt, thyme/oregano and sugar and let sit.\nWhen the toasts are ready, spread the sobrassada across the warm toasts, arrange on the plate and then dress them with the tomato mixture.\nServe immediately.'),(655601,'Penne with Sausage, Tomatoes and Potatoes','https://spoonacular.com/recipeImages/655601-556x370.jpg','','<ol><li>In a large pot add the potatoes and salted water. Bring the water to a boiling and cook the penne pasta until they are al dente.</li><li>Meanwhile in a large deep skillet heat 1 tablespoon of the olive oil until warm, but not smoking.</li><li>Add the sausage to the skillet. Cook on medium high heat until nicely browned and cooked through. About 5 minutes.</li><li>Add the tomatoes and cook over medium low heat.</li><li>Add the pasta and potatoes to the skillet and add the butter and cook stirring until the pasta is well coated.</li><li>Transfer to bowls and serve right the way.</li></ol>'),(655649,'Peppered Goat Cheese Log Sliders','https://spoonacular.com/recipeImages/655649-556x370.jpg','','Sprinkle cracked peppercorns on parchment paper and roll the Chavrie Goat log in the peppercorns to evenly coat the exterior and refrigerate\nWith a 1  oz. scoop separate the ground beef into 12 scant portions and hand form pattys and refrigerate\nPre heat a grill to high\nSlice the plum tomatoes in  inch slices set aside\nSlice the Chavrie Goat Log in  inch slices place on a sheet tray and refrigerate\nSeason the burgers with salt and pepper and grill for 2 minutes on each side\nTo assemble the slider lay out all the bottoms of the brioche rolls\nBegin the stacking process by placing a cooked burger on each roll , top with Chavrie  Goat log slice , plum tomato slice , watercress and drizzle with olive oil and top with roll\nServe warm\nServing Suggestion:\nServe as an appetizer or serve (2) with matchstick potatoes as a light lunch with Heineken beer'),(710765,'How to Make a Chicken Taco Crock Pot','https://spoonacular.com/recipeImages/710765-556x370.jpg','','Instructions\n\nHeat crock pot to the HIGH setting for 4 hours, or LOW setting for 8 hours.\n\nAdd chicken, onion, corn, beans, tomatoes and green chilis, and taco seasoning to the slow cooker. Stir everything well, and then cover and cook. Stir intermittently over the next few hours.\n\nOnce fully cooker, serve on lettuce (for a healthier taco), or crush up taco shells and serve as a taco salad. OR, you could always serve just like a regular taco.\n\nAdd your favorite toppings such as fat free sour cream and cheese, with a little salsa on the side.\n\nTIP: To make this KETO friendly, omit the corn and the beans, and serve on lettuce. It\'s still full of flavor and so delicious! '),(715391,'Slow Cooker Chicken Taco Soup','https://spoonacular.com/recipeImages/715391-556x370.jpg','','Once you have all of your ingredients added, allow it to cook all day for 8 hours on low. If you are wanting to make this a little faster, turn it on high and cook for 4 hours.When your Chicken Taco Soup is ready to serve, add in some crushed tortilla shells, shredded cheddar cheese, and a little sour cream.'),(715521,'Turkey Avocado BLT Salad','https://spoonacular.com/recipeImages/715521-556x370.jpg','',''),(730914,'Basil Infused Balsamic Oven Baked Chicken','https://spoonacular.com/recipeImages/730914-556x370.jpg','','Place into the oven and bake for 40 minutes. Cover with mozzarella cheese and place back into the oven for 10 additional minutes. Use a thermometer make sure the internal temp is 160 for the chicken to be fully cooked. If not, allow to cook for a few additional minutes until the desired temp is reached.Allow to cool slightly and serve with your favorite salad.Basil Infused Balsamic Oven Baked ChickenI LOVE this recipe. If you want to join in on the Simple Fit Forty Lifestyle campaign we are kicking off here on PinkWhen, then make sure you sign up for the newsletter and stay up to date. You can also follow along in our private Facebook community, Simple Fit Forty Lifestyle, where we will be sharing all things fit an healthy and motivating each other along the way.JOIN 500,000 SUBSCRIBERS!Join over 500,000 others who follow PinkWhen on Social Media, the PinkWhen blog, and email. Sign up to receive exclusive bonuses like this FREE Simple Fit Dinners Ebook.Don\'t wait! You won\'t want to miss a thing.Success! Now check your email to confirm your subscription and download your FREE ebook.There was an error submitting your subscription. Please try again.First NameEmail AddressSubscribePowered by ConvertKit'),(1095992,'Turkey Ranch BLT','https://spoonacular.com/recipeImages/1095992-556x370.jpg','','<ol><li>Brush the slices of turkey with ranch dressing.</li><li>Stuff each half of pita with a a few slices of tomato, greens, a slice of turkey, and two slices of bacon.</li><li>Serve.</li></ol>');
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_ingredient`
--

DROP TABLE IF EXISTS `recipe_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_ingredient` (
  `recipe_id` int NOT NULL,
  `ingredientsofrecipe_id` int NOT NULL,
  PRIMARY KEY (`recipe_id`,`ingredientsofrecipe_id`),
  KEY `ingredientsofrecipe_id` (`ingredientsofrecipe_id`),
  CONSTRAINT `recipe_ingredient_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`),
  CONSTRAINT `recipe_ingredient_ibfk_2` FOREIGN KEY (`ingredientsofrecipe_id`) REFERENCES `ingredientsofrecipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_ingredient`
--

LOCK TABLES `recipe_ingredient` WRITE;
/*!40000 ALTER TABLE `recipe_ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipe_ingredient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-20 13:37:23

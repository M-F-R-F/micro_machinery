@echo off
:: Vazkii's JSON creator for blocks
:: Put in your /resources/assets/%modid%/models/block
:: Makes basic block JSON files as well as the acossiated item and simple blockstate
:: Can make multiple blocks at once
::
:: Usage:
:: _make (block name 1) (block name 2) (block name x)
::
:: Change this to your mod's ID
set modid=micro_machinery

setlocal enabledelayedexpansion

for %%x in (%*) do (
	echo Making %%x.json blocks
	(
		echo {
		echo 	"type": "minecraft:block",
		echo 	"pools": [
		echo 		{
		echo 			"rolls": 1,
		echo 			"entries": [
		echo 				{
		echo 					"type": "minecraft:item",
		echo 					"name": "%modid%:%%x"
		echo 				}
		echo 			],
		echo 			"conditions": [
		echo 				{
		echo 					"condition": "minecraft:survives_explosion"
		echo 				}
		echo 			]
		echo 		}
		echo 	]
		echo }
	) > %%x.json

)

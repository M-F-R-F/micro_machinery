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
	echo Making coil%%x.json power_furnace_recipe
	(
		echo {"type": "micro_machinery:power_furnace_recipe",
		echo  "isSingle": true,
		echo  "temperature": 1337,
		echo  "input": [{
		echo    "item": "micro_machinery:coil%%x",
		echo      "count": 1}],
		echo  "output": [
		echo    {"fluidName": "micro_machinery:fluids/molten_%%x",
		echo    "amount": 576}],
		echo  "heat":320}
	) > coil%%x.json
)

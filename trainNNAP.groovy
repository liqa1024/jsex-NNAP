/**
 * This file is part of example scripts of NNAP in jse
 * Copyright 2025 Qing'an Li
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import jse.atom.data.DumpXYZ
import jse.code.IO
import jse.code.UT
import jse.math.matrix.IMatrix
import jse.math.vector.Vectors
import jsex.nnap.Trainer


def trainer = new Trainer([
    elems: ['Cu'],
    ref_engs: [-3.5100],
    nthreads: 8,
    train_basis: true,
    basis: [
        type: 'merge',
        basis: [
            [
                type: 'chebyshev',
                nmax: 6,
                rcut: 7.0,
                wtype: 'exfull'
            ],
            [
                type: 'spherical_chebyshev',
                nmax: 5,
                lmax: 6,
                l3max: 2,
                rcut: 5.0,
                wtype: 'exfull',
                post_fuse: true,
                post_fuse_size: 6
            ],
        ]
    ],
    nn: [
        hidden_dims: [48, 32, 24]
    ]
])

boolean trainForce = true
boolean trainStress = true

for (data in UT.Timer.pbarw('read train', DumpXYZ.read('data/Cu-train.xyz').asList())) {
    def forces = trainForce ? (IMatrix)data.property('forces') : null
    def stress = trainStress ? IO.Text.str2data((String)data.parameter('stress'), 9) : null
    trainer.addTrainData(
        data, (double)data.parameter('energy'),
        trainForce ? forces : null,
        trainStress ? Vectors.from(stress[0], stress[4], stress[8], stress[1], stress[2], stress[5]) : null
    )
}
for (data in UT.Timer.pbarw('read test', DumpXYZ.read('data/Cu-test.xyz').asList())) {
    def forces = trainForce ? (IMatrix)data.property('forces') : null
    def stress = trainStress ? IO.Text.str2data((String)data.parameter('stress'), 9) : null
    trainer.addTestData(
        data, (double)data.parameter('energy'),
        trainForce ? forces : null,
        trainStress ? Vectors.from(stress[0], stress[4], stress[8], stress[1], stress[2], stress[5]) : null
    )
}

trainer.train(1000)

trainer.save('nnpot/Cu.json', true)
IO.cols2csv([trainer.trainLoss(), trainer.testLoss()], 'nnpot/loss-Cu.csv', 'train', 'test')

